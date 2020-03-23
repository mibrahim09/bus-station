/*
 *  Assignment II for BusStation
 *  Team members : Muhammad Ibrahim, hossam tarek, Sherif ahmed
 */
package material;

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
public class Trip {

    String From, To, VehicleInfo, Driver, Stops;
    int Id;

    public String[] getStops() {
        return Stops.split("#");
    }

    public String getStopsStr() {
        return Stops;
    }

    public String getFrom() {
        return From;
    }

    public String getTo() {
        return To;
    }

    public String getVehicleInfo() {
        return VehicleInfo;
    }

    public String getDriver() {
        return Driver;
    }

    public int getDay() {
        return Day;
    }

    public int getMonth() {
        return Month;
    }

    public int getYear() {
        return Year;
    }

    public int getHour() {
        return Hour;
    }

    public int getMinute() {
        return Minute;
    }

    public int getSpots() {
        return Spots;
    }

    public double getPrice() {
        return price;
    }

    public boolean isExternal() {
        return External;
    }
    int Day, Month, Year, Hour, Minute, Spots;
    boolean External;
    double price;

    public Trip(String From, String To, String VehicleInfo, String Driver,
            int Day, int Month, int Year, int Hour, int Minute,
            boolean External, int Spots, String Stops, double price, int Id) {
        this.From = From;
        this.To = To;
        this.VehicleInfo = VehicleInfo;
        this.Driver = Driver;
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
        this.Minute = Minute;
        this.External = External;
        this.Spots = Spots;
        this.Stops = Stops;
        this.price = price;
        this.Id = Id;
    }

    public void SaveTrip() {

        // Didnt find it and will insert new record to the database.
        try {
            Connection conn = DatabaseConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            String query = String.format("Insert into trips values ('0','%s','%s','%s','%s','%d','%d','%d','%d','%d',"
                    + "'%d','%d','%s','%f')",
                    From,
                    To,
                    VehicleInfo,
                    Driver,
                    Day,
                    Month,
                    Year,
                    Hour,
                    Minute,
                    External ? 1 : 0,
                    Spots,
                    getStopsStr(),
                    price);
            System.out.println(query);
            st.execute(query);

            conn.close();
            st.close();
            JOptionPane.showMessageDialog(null, "This trip has been added in the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Trip> Search4Trips(String from, String destination, int day, int month, int year) {
        ArrayList<Trip> trips = new ArrayList<Trip>();
        try {
            Connection conn = DatabaseConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            String query = String.format("select * from trips where TripFrom='%s' AND TripTo='%s' AND "
                    + "Day='%d' AND Month='%d' AND Year='%d'",
                    from, destination, day, month, year);
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // tries to read the data from the database
            while (rs.next()) {
                trips.add(new Trip(rs.getString("TripFrom"),
                        rs.getString("TripTo"),
                        rs.getString("VehicleInfo"),
                        rs.getString("Driver"),
                        Integer.parseInt(rs.getString("Day")),
                        Integer.parseInt(rs.getString("Month")),
                        Integer.parseInt(rs.getString("Year")),
                        Integer.parseInt(rs.getString("Hour")),
                        Integer.parseInt(rs.getString("Minute")),
                        Integer.parseInt(rs.getString("External")) == 1,
                        Integer.parseInt(rs.getString("AvailableSpots")),
                        rs.getString("Stops"),
                        rs.getDouble("Price"),
                        rs.getInt("Id"))
                );
            }

            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trips;
    }

    public void updateSpots() {
        try {
            Connection conn = DatabaseConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            String query = String.format("update trips set AvailableSpots='%d' where Id='%d'", --Spots, Id);

            st.execute(query);

            conn.close();
            st.close();
            JOptionPane.showMessageDialog(null, "This trip has been updated in the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
