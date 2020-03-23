package Employees;

import Interfaces.IEmployee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import system.DatabaseConnection;

public class Driver extends Employee {

    public Driver(String Name, String NationalId, double Salary, int YearOfBirth) {
        super(Name, NationalId, Salary, YearOfBirth);
        // TODO Auto-generated constructor stub
        this.setEmployee(EmployeeType.Drivers);
    }

    public static ArrayList<Driver> LoadAllEmployees() {
        ArrayList<Driver> allDrivers = new ArrayList<Driver>();
        try {
            Connection conn = DatabaseConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery("SELECT * FROM employees where EmpType='0'");

            // tries to read the data from the database
            while (rs.next()) {
                String rName = rs.getString("Name");
                String sNationalId = rs.getString("NationalId");
                Double rSalary = rs.getDouble("Salary");
                int yOB = rs.getInt("YearOfBirth");
                EmployeeType typ = rs.getInt("EmpType") == 0 ? EmployeeType.Drivers : EmployeeType.Managers;
                if (typ == EmployeeType.Drivers) {
                    allDrivers.add(new Driver(rName, sNationalId, rSalary, yOB));
                }
            }

            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allDrivers;

    }
}
