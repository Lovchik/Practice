package by.vit.ban.model;

import by.vit.ban.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WorkshopDiscovery {

    private WorkshopRepository workshopRepository;
    private List<Workshop> workshops;

    @Autowired
    public WorkshopDiscovery(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public WorkshopDiscovery(List<Workshop> workshops) {
        this.workshops = workshops;
    }

    public WorkshopDiscovery() {
    }

    public static List<Double> splitOnElements(String coordinates) {
        List<String> list = Arrays.stream(coordinates.split(", ")).collect(Collectors.toList());
        List<Double> listOfLatitudeLongitude = new ArrayList<>();
        for (String element : list) {
            listOfLatitudeLongitude.add(Double.parseDouble(element));
        }
        return listOfLatitudeLongitude;
    }

    public static double calculateDistance(double xPositionFirstObject, double yPositionFirstObject, double xPositionSecondObject, double yPositionSecondObject) {
        return Math.sqrt(Math.pow((xPositionFirstObject - xPositionSecondObject), 2) + Math.pow((yPositionFirstObject - yPositionSecondObject), 2));
    }

    public Workshop findWorkshopsNearby(List<CarService> carServices, String coordinates, boolean cheaper) {
        workshops = (List<Workshop>) workshopRepository.findAll();
        Map<Workshop, Double> sortedByCordsWorkshops = new HashMap<>();
        double clientLatitude = splitOnElements(coordinates).get(0);
        double clientLongitude = splitOnElements(coordinates).get(1);
        double totalPrice = 0;
        for (Workshop workshop : workshops) {
            if (carServices.stream()
                    .allMatch(requestedCarServices -> workshop.getPriceList()
                            .stream()
                            .anyMatch(carServicesCurrentWorkshop -> requestedCarServices.getName().equals(carServicesCurrentWorkshop.getCarService().getName())))) {
                double workshopLatitude = splitOnElements(workshop.getCoordinates()).get(0);
                double workshopLongitude = splitOnElements(workshop.getCoordinates()).get(1);
                totalPrice=0;
                for (CarService carService : carServices) {
                    for (PricePosition pricePosition : workshop.getPriceList()) {
                        if (carService.getName().equals(pricePosition.getCarService().getName())) {
                            totalPrice += pricePosition.getPrice();
                        }
                    }
                }
                if (cheaper) {
                    sortedByCordsWorkshops.put(workshop, totalPrice);

                } else {
                    sortedByCordsWorkshops.put(workshop, calculateDistance(clientLatitude, clientLongitude, workshopLatitude, workshopLongitude));
                }
            }
        }
        Optional<Map.Entry<Workshop, Double>> workshopOptional = sortedByCordsWorkshops.entrySet().stream()
                .min(Map.Entry.comparingByValue());
        return workshopOptional.map(Map.Entry::getKey).orElse(null);
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<Workshop> workshops) {
        this.workshops = workshops;
    }
}
