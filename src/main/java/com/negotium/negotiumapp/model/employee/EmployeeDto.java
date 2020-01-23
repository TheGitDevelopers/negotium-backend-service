package com.negotium.negotiumapp.model.employee;

import java.util.Objects;

public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private int index;
    private String position;
    private String contractType;
    private String negotiumRole;
    private EmployeeDetails employeeDetails;

    public EmployeeDto() {
    }

    public EmployeeDto(String firstName, String lastName, String position, String contractType, String negotiumRole, EmployeeDetails employeeDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.contractType = contractType;
        this.negotiumRole = negotiumRole;
        this.employeeDetails = employeeDetails;
    }

    public Long getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getNegotiumRole() {
        return negotiumRole;
    }

    public void setNegotiumRole(String negotiumRole) {
        this.negotiumRole = negotiumRole;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(position, that.position) &&
                Objects.equals(contractType, that.contractType) &&
                Objects.equals(negotiumRole, that.negotiumRole) &&
                Objects.equals(employeeDetails, that.employeeDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, position, contractType, negotiumRole, employeeDetails);
    }
}
