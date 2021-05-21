package by.vit.ban;

import by.vit.ban.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WorkshopTest {
    Workshop workshop = new Workshop();

    @Test
    public void possibleToCompleteRequestTest() {
        workshop.setPriceList(new ArrayList<>(Arrays.asList(new PricePosition(new CarService("121"), 20),
                new PricePosition(new CarService("11"), 20))));
        workshop.setMasters(new ArrayList<>(Arrays.asList(new Person(true), new Person(false))));
        Assert.assertTrue(workshop.isPossibleToCompleteRequest(new Request(new ArrayList<>(Collections.singletonList(new CarService("121"))))));
    }

    @Test
    public void noSuchFreeMastersTest() {
        workshop.setPriceList(new ArrayList<>(Arrays.asList(new PricePosition(new CarService("121"), 20),
                new PricePosition(new CarService("11"), 20))));
        workshop.setMasters(new ArrayList<>(Arrays.asList(new Person(false),
                new Person(false))));
        Assert.assertFalse(workshop.isPossibleToCompleteRequest(new Request(new ArrayList<>(Collections.singletonList(new CarService("121"))))));
    }

    @Test(expected = NullPointerException.class)
    public void noSuchRequestedService() {
        workshop.setPriceList(new ArrayList<>(Arrays.asList(new PricePosition(new CarService("1232"), 20),
                new PricePosition(new CarService("11"), 20))));
        workshop.setMasters(new ArrayList<>(Collections.singletonList(new Person(true))));
        Assert.assertFalse(workshop.isPossibleToCompleteRequest(new Request()));
    }

    @Test
    public void getFreeMasterTest() {
        workshop.setMasters(new ArrayList<>(Collections.singletonList(new Person(true))));
        Assert.assertTrue(workshop.getFreeMaster().getStatus());
    }

    @Test
    public void getPricesTest() {
        workshop.setPriceList(new ArrayList<>(Arrays.asList(
                new PricePosition(new CarService("repair wheel"), 20),
                new PricePosition(new CarService("diagnostic"), 12),
                new PricePosition(new CarService("hood repair"), 200))));
        List<CarService> carServices = new ArrayList<>(Arrays.asList(new CarService("repair wheel"),
                new CarService("hood repair")));
        Assert.assertEquals(220, workshop.getTotalPriceByCarServices(carServices), 0);
    }
}

