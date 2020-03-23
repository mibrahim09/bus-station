package Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import system.DatabaseConnection;

public class Customers {

    String name, nationalId, password;
    int yearOfBirth;

    public Customers(String name, String nationalId, String password, int yearOfBirth) {
        super();
        this.name = name;
        this.nationalId = nationalId;
        this.password = password;
        this.yearOfBirth = yearOfBirth;
    }

    public static boolean validateLogin(String Id, String pass) {
        boolean found = false;
        try {
            Connection conn = DatabaseConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM customers where NationalId='" + Id + "' AND Password='" + pass + "'");

            // tries to read the data from the database
            if (rs.next()) {
                found = true;// Found the user..
            }
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    public int getAge() {
        return LocalDateTime.now().getYear() - yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public static Customers loadCustomer(String sNationalId) {
        Customers c = null;
        try {
            Connection conn = DatabaseConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery("SELECT * FROM customers where NationalId='" + sNationalId + "'");

            // tries to read the data from the database
            if (rs.next()) {
                String rName = rs.getString("Name");
                String rPassword = rs.getString("Password");
                int yOB = rs.getInt("YearOfBirth");
                c = new Customers(rName,
                        sNationalId,
                        rPassword,
                        yOB);
            }

            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public void SaveToDB() {
        if (loadCustomer(this.nationalId) != null) {
            // Found the customer in database.
            try {
                Connection conn = DatabaseConnection.getConnection();

                // create the java statement
                Statement st = conn.createStatement();

                // execute the query, and get a java resultset
                String query = String.format(
                        "update customer set Name=%s,Password=%s,YearOfBirth=%d where NationalId=%s",
                        getName(), password, yearOfBirth, getNationalId());

                st.execute(query);

                conn.close();
                st.close();
                JOptionPane.showMessageDialog(null, "This customer has been updated in the database.");
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
                String query = String.format("Insert into customers values ('%s','%s','%s','%d')", getName(),
                        getNationalId(), password, yearOfBirth
                );

                st.execute(query);

                conn.close();
                st.close();
                JOptionPane.showMessageDialog(null, "This customer has been added in the database.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getYear() {
        return yearOfBirth;
    }

}
