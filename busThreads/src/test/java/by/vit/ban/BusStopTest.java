package by.vit.ban;

import org.junit.Assert;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class BusStopTest {

    @Test
    public void busLoadingPassengersAfterGetInTest() {
        BusStop busStopFirst = new BusStop(3);
        Bus bus = new Bus(busStopFirst);
        bus.loadingPassengers();
        Assert.assertEquals(BusStatus.LOADING_PASSENGERS, bus.getBusStatus());
    }


    @Test
    public void busStopEnteringWithNoPlaceTest() {
        BusStop busStop = new BusStop(0);
        Assert.assertFalse(busStop.enter());
    }

    @Test
    public void busStopAreNotExistTest() {
        Bus bus = new Bus(null);
        bus.start();
        Assert.assertEquals(BusStatus.NOT_BEHIND_BUS_STOP, bus.getBusStatus());
    }

    @Test
    public void noFreePlacesOnBusStopTest() {
        BusStop busStop = new BusStop(0);
        Bus bus = new Bus(busStop);
        bus.start();
        await().pollInterval(1, SECONDS)
                .until(() -> bus.getBusStatus() != BusStatus.NOT_BEHIND_BUS_STOP);
        Assert.assertEquals(BusStatus.WAITING_TO_GET_IN, bus.getBusStatus());
    }

    @Test
    public void severalBusesEnteringTest() {
        BusStop busStop = new BusStop(2);
        Bus firstBus = new Bus(busStop);
        Bus secondBus = new Bus(busStop);
        firstBus.start();
        secondBus.start();
        await().pollInterval(1, SECONDS)
                .until(() -> secondBus.getBusStatus() != BusStatus.NOT_BEHIND_BUS_STOP && firstBus.getBusStatus() != BusStatus.NOT_BEHIND_BUS_STOP);
        Assert.assertEquals(BusStatus.DROVE_TO_STOP, firstBus.getBusStatus());
        await().until(() -> !secondBus.isAlive() && !firstBus.isAlive());
    }

    @Test
    public void busLeavingBusStopTest() {
        BusStop busStop = new BusStop(1);
        Bus bus = new Bus(busStop);
        bus.start();
        await().pollInterval(1, SECONDS)
                .until(() -> bus.getBusStatus() != BusStatus.LOADING_PASSENGERS && bus.getBusStatus() != BusStatus.DROVE_TO_STOP);
        Assert.assertEquals(BusStatus.LEFT_THE_STOP, bus.getBusStatus());
        await().until(() -> !bus.isAlive());
    }
}
