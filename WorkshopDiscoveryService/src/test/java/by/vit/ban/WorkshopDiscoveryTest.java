package by.vit.ban;

import by.vit.ban.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WorkshopDiscoveryTest {

    @Test
    public void calculateDistanceSamePositionsTest() {
        Assert.assertEquals(0, WorkshopDiscovery.calculateDistance(1, 2, 1, 2), 0);
    }

    @Test
    public void calculateDistanceNegativeCoordinatesTest() {
        Assert.assertEquals(3.605551275463989, WorkshopDiscovery.calculateDistance(5, -1, 2, -3), 0);
    }

    @Test
    public void splitOnElementsTest() {
        List<Double> expectedValues = new ArrayList<>(Arrays.asList(53.90958255529801, 27.59613084798946));
        Assert.assertEquals(expectedValues, WorkshopDiscovery.splitOnElements("53.90958255529801, 27.59613084798946"));
    }

    @Test
    public void findWorkShopBehindTest() {
        List<CarService> carServices = new ArrayList<>(Arrays.asList(new CarService("Wheel Repair"), new CarService("Diagnostic")));
        WorkshopDiscovery workshopDiscovery = new WorkshopDiscovery();
        Workshop workshop = new Workshop();
        workshopDiscovery.setWorkshops(new ArrayList<>());
        workshopDiscovery.getWorkshops().add(workshop);
        workshop.setCoordinates("53.90895001819387, 27.548495682582672");
        workshop.setPriceList(new ArrayList<>(Arrays.asList(new PricePosition(carServices.get(0), 20),
                new PricePosition(carServices.get(1), 20))));
        workshop.setMasters(new ArrayList<>(Collections.singletonList(new Person(true))));
        Assert.assertEquals(workshop, workshopDiscovery.findWorkshopsNearby(carServices, "53.89685899892551, 27.548157397445134",false));
    }
}

