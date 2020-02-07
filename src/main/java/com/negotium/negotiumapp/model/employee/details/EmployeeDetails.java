package com.negotium.negotiumapp.model.employee.details;

import com.negotium.negotiumapp.model.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee_details")
    private Long id;

    @Column(name = "phone_number")
    @Max(value = 32)
    private int phoneNumber;

    @Column(name = "email_address")
    @Size(max = 64)
    private String email;

    @Column(name = "holiday")
    private double holiday;

    @Column(name = "hours_worked")
    private double hoursWorked;

    @Column(name = "salary")
    private double salary;

    @Column(name = "position")
    private String position;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "negotium_role")
    private String negotiumRole;

    @OneToOne(mappedBy = "employeeDetails")
    private Employee employee;

    public EmployeeDetails() {
    }

    EmployeeDetails(EmpDetailsBuilder builder) {
        this.salary = builder.salary;
        this.position = builder.position;
        this.phoneNumber = builder.phoneNumber;
        this.negotiumRole = builder.negotiumRole;
        this.hoursWorked = builder.hoursWorked;
        this.holiday = builder.holiday;
        this.employee = builder.employee;
        this.email = builder.email;
        this.contractType = builder.contractType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getHoliday() {
        return holiday;
    }

    public void setHoliday(double holiday) {
        this.holiday = holiday;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDetails)) return false;
        EmployeeDetails details = (EmployeeDetails) o;
        return phoneNumber == details.phoneNumber &&
                Double.compare(details.holiday, holiday) == 0 &&
                Double.compare(details.hoursWorked, hoursWorked) == 0 &&
                Double.compare(details.salary, salary) == 0 &&
                Objects.equals(id, details.id) &&
                Objects.equals(email, details.email) &&
                Objects.equals(position, details.position) &&
                Objects.equals(contractType, details.contractType) &&
                Objects.equals(negotiumRole, details.negotiumRole) &&
                Objects.equals(employee, details.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee.getId(), employee.getName(), employee.getEmployeeIndex());
    }
}