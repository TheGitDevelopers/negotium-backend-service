package com.negotium.negotiumapp.model.employee;

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
    @Max(value= 32)
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

    public EmployeeDetails(){

    }

    private EmployeeDetails(Builder builder){
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
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDetails that = (EmployeeDetails) o;
        return phoneNumber == that.phoneNumber &&
                holiday == that.holiday &&
                Double.compare(that.hoursWorked, hoursWorked) == 0 &&
                Double.compare(that.salary, salary) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(position, that.position) &&
                Objects.equals(contractType, that.contractType) &&
                Objects.equals(negotiumRole, that.negotiumRole) &&
                Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, email, holiday, hoursWorked, salary, position, contractType, negotiumRole, employee);
    }

    public static final class Builder {

        private int phoneNumber;
        private String email;
        private int holiday;
        private double hoursWorked;
        private double salary;
        private String position;
        private String contractType;
        private String negotiumRole;
        private Employee employee;

        public Builder phoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder holiday(int holiday) {
            this.holiday = holiday;
            return this;
        }

        public Builder hoursWorked(double hoursWorked) {
            this.hoursWorked = hoursWorked;
            return this;
        }

        public Builder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder contractType(String contractType) {
            this.contractType = contractType;
            return this;
        }

        public Builder negotiumRole(String negotiumRole) {
            this.negotiumRole = negotiumRole;
            return this;
        }

        public Builder employee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public EmployeeDetails build(){
            if(employee.getName().isEmpty()){
                throw new IllegalStateException("Employee cannot be empty and he must have name");
            }
            EmployeeDetails employeeDetails = new EmployeeDetails(this);
            employeeDetails.contractType = contractType;
            employeeDetails.email = email;
            employeeDetails.employee = employee;
            employeeDetails.holiday = holiday;
            employeeDetails.hoursWorked = hoursWorked;
            employeeDetails.negotiumRole = negotiumRole;
            employeeDetails.phoneNumber = phoneNumber;
            employeeDetails.position = position;
            employeeDetails.salary =salary;
            return employeeDetails;
        }
    }
}