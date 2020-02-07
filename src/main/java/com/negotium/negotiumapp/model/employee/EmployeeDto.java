package com.negotium.negotiumapp.model.employee;

import com.negotium.negotiumapp.model.employee.details.EmployeeDetails;

import java.util.Objects;

public class EmployeeDto {

    private Long id;
    private String name;
    private int employeeIndex;
    private EmployeeDetails employeeDetails = new EmployeeDetails();

    public EmployeeDto() {
    }

    public EmployeeDto(String name, int employeeIndex, EmployeeDetails employeeDetails) {
        this.name = name;
        this.employeeIndex = employeeIndex;
        this.employeeDetails = employeeDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeIndex() {
        return employeeIndex;
    }

    public void setEmployeeIndex(int employeeIndex) {
        this.employeeIndex = employeeIndex;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return employeeIndex == that.employeeIndex &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(employeeDetails, that.employeeDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employeeIndex, employeeDetails);
    }
}