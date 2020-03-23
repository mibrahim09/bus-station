/*
 *  Assignment II for BusStation
 *  Team members : Muhammad Ibrahim, hossam tarek, Sherif ahmed
 */
package material;

import Entities.Customers;
import static Entities.Customers.loadCustomer;
import Entities.Gender;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import system.DatabaseConnection;

/**
 *
 * @author Muhammad
 */
public class Vehicle {

    VehicleTypes myVehicleType;

    public VehicleTypes getMyVehicleType() {
        return myVehicleType;
    }

    public int getNumber() {
        return number;
    }
    int number;

    public Vehicle(VehicleTypes myVehicleType, int number) {
        this.myVehicleType = myVehicleType;
        this.number = number;
    }

    public static VehicleTypes getVehicleType(int no) {
        switch (no) {
            case 0:
                return VehicleTypes.Bus;
            case 1:
                return VehicleTypes.MiniBus;
            case 2:
                return VehicleTypes.Limo;
        }
        return VehicleTypes.Bus;
    }

    public static int getVehicleType(VehicleTypes no) {
        switch (no) {
            case Bus:
                return 0;
            case MiniBus:
                return 1;
            case Limo:
                return 2;
        }
        return 0;
    }

    public int getPlaces() {
        switch (myVehicleType) {
            case Bus:
                return 55;
            case MiniBus:
                return 20;
            case Limo:
                return 4;
        }
        return 0;
    }

    public static Vehicle LoadVehicle(int num) {
        Vehicle v = null;
        try {
            Connection conn = DatabaseConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery("SELECT * FROM vehicles where Number='" + num + "'");

            // tries to read the data from the database
            if (rs.next()) {
                int no = rs.getInt("Number");
                int vehicleType = rs.getInt("VehicleType");
                v = new Vehicle(getVehicleType(vehicleType), no);
            }

            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public static ArrayList<Vehicle> LoadAllVehicles() {
        ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
        try {
            Connection conn = DatabaseConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery("SELECT * FROM vehicles");

            // tries to read the data from the database
            while (rs.next()) {
                int no = rs.getInt("Number");
                int vehicleType = rs.getInt("VehicleType");
                allVehicles.add(new Vehicle(getVehicleType(vehicleType), no));
            }
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allVehicles;
    }

    public void SaveToDB() {
        if (LoadVehicle(this.number) != null) {
            // Found the customer in database.
            try {
                Connection conn = DatabaseConnection.getConnection();

                // create the java statement
                Statement st = conn.createStatement();

                // execute the query, and get a java resultset
                String query = String.format(
                        "update vehicles set VehicleType=%d where Number=%d",
                        getNumber(),
                        getVehicleType(getMyVehicleType()));

                st.execute(query);

                conn.close();
                st.close();
                JOptionPane.showMessageDialog(null, "This vehicle has been updated in the database.");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // Didnt find it and will insert new record to the database.
            try {
                Connection conn = DatabaseConnection.getConnection();

                // create the java statement
                Statement st = conn.createStatement();

                // execute the query, and get a java resultset
                String query = String.format("Insert into vehicles values ('%d','%d')", getNumber(),
                        getVehicleType(getMyVehicleType()));

                st.execute(query);

                conn.close();
                st.close();
                JOptionPane.showMessageDialog(null, "This vehicle has been added in the database.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
