package com.negotium.negotiumapp.model.employee;

import com.negotium.negotiumapp.model.employee.details.EmployeeDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long id;

    @Column(name = "employee_name")
    @Size(max = 32)
    @NotNull
    private String name;

    @Column(name = "employee_index", unique = true)
    @NotNull
    private int employeeIndex;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_details")
    private EmployeeDetails employeeDetails;

    public Employee() {
    }

    public Employee(String name, int employeeIndex, EmployeeDetails employeeDetails) {
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
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return employeeIndex == employee.employeeIndex &&
                Objects.equals(id, employee.id) &&
                name.equals(employee.name) &&
                Objects.equals(employeeDetails, employee.employeeDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employeeIndex);
    }
}