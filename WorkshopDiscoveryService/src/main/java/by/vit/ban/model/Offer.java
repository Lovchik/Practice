package by.vit.ban.model;

import java.util.List;
import java.util.Objects;

public class Offer {
    private double totalPrice;
    private List<CarService> carServices;
    private Workshop workshop;

    public Offer(double totalPrice, List<CarService> carServices, Workshop workshop) {
        this.totalPrice = totalPrice;
        this.carServices = carServices;
        this.workshop = workshop;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CarService> getCarServices() {
        return carServices;
    }

    public void setCarServices(List<CarService> carServices) {
        this.carServices = carServices;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Double.compare(offer.totalPrice, totalPrice) == 0 && Objects.equals(carServices, offer.carServices) && Objects.equals(workshop, offer.workshop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPrice, carServices, workshop);
    }
}
