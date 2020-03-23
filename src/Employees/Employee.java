package Employees;

import Employees.Employee.EmployeeType;
import Entities.Customers;
import static Entities.Customers.loadCustomer;
import Entities.Gender;
import Interfaces.IEmployee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import system.DatabaseConnection;

public abstract class Employee implements IEmployee {

    public enum EmployeeType {
        Drivers, Managers
    };

    EmployeeType employeeType;
    String name;
    double Salary;
    int yearOfBirth;
    String nationalId;

    public Employee(String Name, String NationalId, double Salary, int yearOfBirth) {
        this.name = Name;
        this.Salary = Salary;
        this.yearOfBirth = yearOfBirth;
        this.nationalId = NationalId;
    }

    protected void setEmployee(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getName() {
        return name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double newSalary) {
        this.Salary = newSalary;
    }

    public static IEmployee LoadEmployee(String sNationalId) {
        IEmployee emp = null;
        try {
            Connection conn = DatabaseConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery("SELECT * FROM employees where NationalId='" + sNationalId + "'");

            // tries to read the data from the database
            if (rs.next()) {
                String rName = rs.getString("Name");
                Double rSalary = rs.getDouble("Salary");
                int yOB = rs.getInt("YearOfBirth");
                EmployeeType typ = rs.getInt("EmpType") == 0 ? EmployeeType.Drivers : EmployeeType.Managers;
                if (typ == EmployeeType.Drivers) {
                    emp = new Driver(rName, sNationalId, rSalary, yOB);
                }
            }

            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;

    }

    public void SaveEmployee() {
        if (LoadEmployee(this.nationalId) != null) {
            // Found the customer in database.
            try {
                Connection conn = DatabaseConnection.getConnection();

                // create the java statement
                Statement st = conn.createStatement();

                // execute the query, and get a java resultset
                String query = String.format(
                        "update employees set Name=%s,YearOfBirth=%d,Salary=%f,EmpType=%d where NationalId=%s",
                        getName(), yearOfBirth, getSalary(), this.employeeType == EmployeeType.Drivers ? 0 : 1, getNationalId());

                st.execute(query);

                conn.close();
                st.close();
                JOptionPane.showMessageDialog(null, "This Employee has been updated in the database.");
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
                String query = String.format("Insert into employees values ('%s','%s','%d','%f','%d')",
                        getName(), getNationalId(), yearOfBirth, getSalary(), this.employeeType == EmployeeType.Drivers ? 0 : 1);

                st.execute(query);

                conn.close();
                st.close();
                JOptionPane.showMessageDialog(null, "This Employee has been added in the database.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
