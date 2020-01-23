package com.negotium.negotiumapp.model.employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long id;

    private int index;

    @Column(name = "first_name")
    @Size(max = 32)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "position")
    private String position;

    @Column(name = "concrat_type")
    private String contractType;

    @Column(name = "negotium_role")
    private String negotiumRole;

    @Column(name = "employee_details")
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "employee", fetch = FetchType.EAGER)
    private EmployeeDetails employeeDetails;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String position, String contractType, String negotiumRole, EmployeeDetails employeeDetails) {
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
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(contractType, employee.contractType) &&
                Objects.equals(negotiumRole, employee.negotiumRole) &&
                Objects.equals(employeeDetails, employee.employeeDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, position, contractType, negotiumRole, employeeDetails);
    }
}
