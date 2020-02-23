package com.negotium.negotiumapp.model.user.employee.request;

import com.negotium.negotiumapp.model.user.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "holiday_request")
public class HolidayRequest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_request")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @NotNull
    @Column(name = "end_date")
    private OffsetDateTime endDate;

    @NotNull
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    public HolidayRequest(){}

    public HolidayRequest(Employee employee, OffsetDateTime startDate, OffsetDateTime endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "HolidayRequest{" +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", requestStatus=" + requestStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolidayRequest that = (HolidayRequest) o;
        return Objects.equals(employee, that.employee) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                requestStatus == that.requestStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, startDate, endDate, requestStatus);
    }
}