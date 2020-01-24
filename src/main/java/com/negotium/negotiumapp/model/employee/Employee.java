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

    @Column(name = "first_name")
    @Size(max = 32)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "person_id_number")
    @NotNull
    @Size(max = 32)
    private int personIdNumber;

    @Column(name = "employee_details")
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "employee", fetch = FetchType.EAGER)
    private EmployeeDetails employeeDetails;

    public Employee() {
    }

    public Employee(String firstName, String lastName, int personIdNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.personIdNumber = personIdNumber;
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
        Employee employee = (Employee) o;
        return personIdNumber == employee.personIdNumber &&
                Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(employeeDetails, employee.employeeDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, personIdNumber, employeeDetails);
    }
}
