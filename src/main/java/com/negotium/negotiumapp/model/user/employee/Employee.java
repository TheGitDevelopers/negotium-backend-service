package com.negotium.negotiumapp.model.user.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.negotium.negotiumapp.model.user.employee.details.EmployeeDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @Column(name = "employee_index")
    private int employeeIndex;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
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
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeIndex=" + employeeIndex +
                '}';
    }

}
