/*
 *  Assignment II for BusStation
 *  Team members : Muhammad Ibrahim, ossam tarek, Sherif ahmedossam tarek, Sherif ahmed
 */
package material;

import Entities.Customers;

/**
 *
 * @author Muhammad
 */
public class Tickets {

    Customers passenger;
    Trip myTrip;

    public Customers getPassenger() {
        return passenger;
    }

    public Trip getMyTrip() {
        return myTrip;
    }

    public boolean isRounded() {
        return rounded;
    }
    boolean rounded;

    public Tickets(Trip myTrip, Customers passenger, boolean rounded) {
        this.passenger = passenger;
        this.myTrip = myTrip;
        this.rounded = rounded;
    }

}
