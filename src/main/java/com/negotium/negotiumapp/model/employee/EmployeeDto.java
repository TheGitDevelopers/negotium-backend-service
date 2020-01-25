package com.negotium.negotiumapp.model.employee;

import java.util.Objects;

public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private int personIdNumber;
    private EmployeeDetails employeeDetails;

    public EmployeeDto() {
    }

    public EmployeeDto(String firstName, String lastName, int personIdNumber, EmployeeDetails employeeDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personIdNumber = personIdNumber;
        this.employeeDetails = employeeDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPersonIdNumber() {
        return personIdNumber;
    }

    public void setPersonIdNumber(int personIdNumber) {
        this.personIdNumber = personIdNumber;
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
        return personIdNumber == that.personIdNumber &&
                Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(employeeDetails, that.employeeDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, personIdNumber, employeeDetails);
    }
}
