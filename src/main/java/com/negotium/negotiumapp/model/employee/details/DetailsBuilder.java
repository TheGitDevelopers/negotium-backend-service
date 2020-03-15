package com.negotium.negotiumapp.model.employee.details;

import com.negotium.negotiumapp.model.employee.Employee;

public interface DetailsBuilder {

    void setPhoneNumber(int phoneNumber);
    void setEmail(String email);
    void setHoliday(double holiday);
    void setHoursWorked(double hoursWorked);
    void setSalary(double salary);
    void setPosition(String position);
    void setContractType(String contractType);
    void setNegotiumRole(String negotiumRole);
    void setEmployee(Employee employee);
}