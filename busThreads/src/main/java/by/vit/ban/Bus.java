package by.vit.ban;

import lombok.SneakyThrows;
import org.apache.log4j.Logger;

/**
 * Class which shows how buses stop at the bus stops.
 */
public class Bus extends Thread {

    private static final Logger logger = Logger.getLogger(Bus.class);
    private final BusStop busStop;
    private BusStatus busStatus;

    public Bus(BusStop busStop) {
        this.busStop = busStop;
        setBusStatus(BusStatus.NOT_BEHIND_BUS_STOP);
    }

    public BusStatus getBusStatus() {
        return busStatus;
    }

    public void setBusStatus(BusStatus busStatus) {
        this.busStatus = busStatus;
    }

    /**
     * Shows how bus trying get in to bus stop,loading passengers and left the bus stop.
     */
    @Override
    public void run() {
        tryingToComeOnBusStop();
        droveToStop();
        loadingPassengers();
        leavingTheBusStop();
    }

    /**
     * Bus trying to get in to bus stop
     */
    @SneakyThrows
    private void tryingToComeOnBusStop() {
        while (!busStop.enter()) {
            logger.info(Thread.currentThread().getId() + " автобус ждет");
            setBusStatus(BusStatus.WAITING_TO_GET_IN);
            synchronized (busStop) {
                busStop.wait();
            }
        }
    }

    /**
     * leaving the bus stop
     */
    private void leavingTheBusStop() {
        logger.info(Thread.currentThread().getId() + " автобус уехал");
        setBusStatus(BusStatus.LEFT_THE_STOP);
        busStop.leave();

    }

    /**
     * Comes to a bus stop
     */
    @SneakyThrows
    private void droveToStop() {
        logger.info(Thread.currentThread().getId() + " автобус заехал на остановку");
        setBusStatus(BusStatus.DROVE_TO_STOP);
        sleep(2000);
    }

    /**
     * loading passengers at the bus stop
     */
    @SneakyThrows
    public void loadingPassengers() {
        setBusStatus(BusStatus.LOADING_PASSENGERS);
        logger.info(Thread.currentThread().getId() + " автобус загружает пассажиров");
        sleep(2000);
    }
}
