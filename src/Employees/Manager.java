package Employees;

import Employees.Employee.EmployeeType;

public class Manager extends Employee {

    public Manager(String Name, String NationalId, double Salary, int YearOfBirth) {
        super(Name, NationalId, Salary, YearOfBirth);
        // TODO Auto-generated constructor stub
        this.setEmployee(EmployeeType.Managers);
    }

}
