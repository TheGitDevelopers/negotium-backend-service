package com.negotium.negotiumapp.model.employee.details;

import com.negotium.negotiumapp.model.employee.Employee;

public class EmpDetailsBuilder {

    int phoneNumber;
    String email;
    int holiday;
    double hoursWorked;
    double salary;
    String position;
    String contractType;
    String negotiumRole;
    Employee employee;

    public EmpDetailsBuilder phoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public EmpDetailsBuilder email(String email) {
        this.email = email;
        return this;
    }

    public EmpDetailsBuilder holiday(int holiday) {
        this.holiday = holiday;
        return this;
    }

    public EmpDetailsBuilder hoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
        return this;
    }

    public EmpDetailsBuilder salary(double salary) {
        this.salary = salary;
        return this;
    }

    public EmpDetailsBuilder position(String position) {
        this.position = position;
        return this;
    }

    public EmpDetailsBuilder contractType(String contractType) {
        this.contractType = contractType;
        return this;
    }

    public EmpDetailsBuilder negotiumRole(String negotiumRole) {
        this.negotiumRole = negotiumRole;
        return this;
    }

    public EmpDetailsBuilder employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public EmployeeDetails build() {
        if (employee.getName().isEmpty()) {
            throw new IllegalStateException("Employee cannot be empty and he must have name");
        }
        return new EmployeeDetails(this);
    }
}
