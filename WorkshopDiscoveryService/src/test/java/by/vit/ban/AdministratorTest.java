package by.vit.ban;

import by.vit.ban.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class AdministratorTest {
    Person administrator = new Person();


    @Test
    public void createContractFromLastConditionalRequestTest() {
        administrator.setWorkPlace(new Workshop());
        administrator.getWorkPlace().setContracts(new ArrayList<>());
        administrator.getWorkPlace().setMasters(new ArrayList<>(Collections.singletonList(new Person(true))));
        administrator.getWorkPlace().setPriceList(new ArrayList<>(Arrays.asList(new PricePosition(new CarService("Repair"), 20),
                new PricePosition(new CarService("11"), 20))));
        administrator.getWorkPlace().setRequests(new LinkedList<>(Collections.singletonList(new Request(new ArrayList<>(Collections.singletonList(new CarService("Repair"))), new Person()))));
        Assert.assertNotNull(administrator.createContractFromLastConditionalRequest(new Request(new ArrayList<>(Collections.singletonList(new CarService("Repair"))))));
    }
}
