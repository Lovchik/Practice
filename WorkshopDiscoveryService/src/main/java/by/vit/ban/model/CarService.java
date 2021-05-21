package by.vit.ban.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "serviceName", nullable = false)
    private String name;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "request_service", joinColumns = @JoinColumn(name = "idService"), inverseJoinColumns = @JoinColumn(name = "idRequest"))
    private List<Request> requests;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carService")
    private List<PricePosition> pricePosition;

    public CarService() {
    }

    public CarService(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", requests=" + requests +
                ", pricePosition=" + pricePosition +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<PricePosition> getPricePosition() {
        return pricePosition;
    }

    public void setPricePosition(List<PricePosition> pricePosition) {
        this.pricePosition = pricePosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
