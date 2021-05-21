package by.vit.ban.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "workshop")
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "workshopName", nullable = false)
    private String workshopName;

    @Column(name = "address", nullable = false)
    private String address;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "workPlace")
    private List<Person> masters;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workshop")
    private List<PricePosition> priceList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workshop")
    private List<Request> requests;

    @Column(name = "coordinates", nullable = false)
    private String coordinates;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workshop")
    private List<Contract> contracts;

    public Workshop(String coordinates) {
        this.coordinates = coordinates;
    }

    public Workshop() {
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Person> getMasters() {
        return masters;
    }

    public void setMasters(List<Person> masters) {
        this.masters = masters;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PricePosition> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PricePosition> priceList) {
        this.priceList = priceList;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public double getTotalPriceByCarServices(List<CarService> carServices) {
        double totalPrice = 0;
        for (CarService carService : carServices) {
            for (PricePosition pricePosition : getPriceList()) {
                if (carService.getName().equals(pricePosition.getCarService().getName())) {
                    totalPrice += pricePosition.getPrice();
                }
            }
        }
        return totalPrice;
    }

    public boolean isPossibleToCompleteRequest(Request request) {
        return request.getCarServices().stream()
                .allMatch(requestedServices -> getPriceList().stream()
                        .anyMatch(workshopsServices -> requestedServices.getName().equals(workshopsServices.getCarService().getName()))) &&
                masters.stream().anyMatch(Person::getStatus);
    }

    @JsonIgnore
    public Person getFreeMaster() {
        Optional<Person> optionalPerson = masters.stream().findAny().filter(Person::getStatus);
        return optionalPerson.orElse(null);
    }

}
