package by.vit.ban.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "priceposition")
public class PricePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="idService")
    private CarService carService;

    @Column(name = "price",nullable = false)
    private double price;

    @JsonIgnore
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="idWorkshop")
    private Workshop workshop;

    public PricePosition(CarService carService, double price) {
        this.carService = carService;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PricePosition() {
    }
}
