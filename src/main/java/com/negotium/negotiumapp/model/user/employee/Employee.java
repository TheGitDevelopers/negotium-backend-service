package com.negotium.negotiumapp.model.user.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.negotium.negotiumapp.model.user.employee.details.EmployeeDetails;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
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

    @Column(name = "employee_index")
    private int employeeIndex;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_details")
    private EmployeeDetails employeeDetails;

    @OneToMany(mappedBy = "employee")
    private List<HolidayRequest> holidayRequest;

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

    public List<HolidayRequest> getHolidayRequest() {
        return holidayRequest;
    }

    public void setHolidayRequest(List<HolidayRequest> holidayRequest) {
        this.holidayRequest = holidayRequest;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeIndex=" + employeeIndex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeIndex == employee.employeeIndex &&
                Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(employeeDetails, employee.employeeDetails) &&
                Objects.equals(holidayRequest, employee.holidayRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employeeIndex, employeeDetails, holidayRequest);
    }
}