package by.vit.ban.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "idClient")
    private Person client;

    @ManyToMany
    @JoinTable( name = "request_service", joinColumns = @JoinColumn(name = "idRequest"), inverseJoinColumns = @JoinColumn(name = "idService"))
    private List<CarService> carServices;

    @OneToOne(mappedBy = "request")
    private Contract contract;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "idWorkshop")
    private Workshop workshop;

    public Request(List<CarService> carServices) {
        this.carServices = carServices;
    }

    public Request() {

    }

    public Request(List<CarService> carServices, Person client) {
        this.client = client;
        this.carServices = carServices;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public List<CarService> getCarServices() {
        return carServices;
    }

    public void setCarServices(List<CarService> carServices) {
        this.carServices = carServices;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", client=" + client +
                ", carServices=" + carServices +
                ", contract=" + contract +
                ", workshop=" + workshop +
                '}';
    }
}
