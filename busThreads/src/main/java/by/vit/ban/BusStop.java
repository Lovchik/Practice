package by.vit.ban;

/**
 * Represents a bus stop with some free places on it
 */
public class BusStop {

    private final int maxPlacesOnBusStop;
    private int placesInCurrentMoment;

    public BusStop(int maxPlacesOnBusStop) {
        this.maxPlacesOnBusStop = maxPlacesOnBusStop;
        this.placesInCurrentMoment = 0;
    }

    /**
     * Lets in at bus stop
     * update free places
     */
    public synchronized boolean enter() {
        if (placesInCurrentMoment == maxPlacesOnBusStop) {
            return false;
        } else {
            placesInCurrentMoment++;
            return true;
        }
    }

    /**
     * Update free places when bus left the bus stop
     */
    public synchronized void leave() {
        placesInCurrentMoment--;
        notifyAll();
    }
}
