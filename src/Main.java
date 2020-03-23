
import Entities.Customers;
import forms.*;

/*
 *  Assignment II for BusStation
 *  Team members : Muhammad Ibrahim, Hossam tarek, Sherif ahmed
 */
/**
 *
 * @author Muhammad
 */
public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
