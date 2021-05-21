package by.vit.ban;

import by.vit.ban.model.Contract;
import by.vit.ban.model.Person;
import by.vit.ban.model.Status;
import org.junit.Assert;
import org.junit.Test;

public class MasterTest {

    @Test
    public void contractStatusChangeTest(){
        Person master = new Person(false);
        Contract contract = new Contract(master, Status.ACCEPTED);
        master.completedRepair(contract);
        Assert.assertEquals(Status.COMPLETED,contract.getStatus());
    }

    @Test
    public void masterStatusChangeTest(){
        Person master = new Person(false);
        Contract contract = new Contract(master,Status.ACCEPTED);
        master.completedRepair(contract);
        Assert.assertTrue(master.getStatus());
    }
}
