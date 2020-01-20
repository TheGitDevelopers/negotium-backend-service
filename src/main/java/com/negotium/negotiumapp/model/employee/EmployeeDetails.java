package com.negotium.negotiumapp.model.employee;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    @Size(max = 32)
    private int phoneNumber;

    @Column(name = "email_address")
    @Size(max = 64)
    private String email;

    @Column(name = "holiday")
    private int holiday;

    @Column(name = "salary")
    private double salary;

    @Column(name = "hours_worked")
    private double hoursWorked;

    @OneToOne
    private Employee employee;

    public EmployeeDetails() {
    }

    public EmployeeDetails(@Size(max = 32) int phoneNumber, @Size(max = 64) String email, int holiday, double salary, double hoursWorked, Employee employee) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.holiday = holiday;
        this.salary = salary;
        this.hoursWorked = hoursWorked;
        this.employee = employee;
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

    public int getHoliday() {
        return holiday;
    }

    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
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
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDetails that = (EmployeeDetails) o;
        return phoneNumber == that.phoneNumber &&
                holiday == that.holiday &&
                Double.compare(that.salary, salary) == 0 &&
                Double.compare(that.hoursWorked, hoursWorked) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, email, holiday, salary, hoursWorked, employee);
    }
}
