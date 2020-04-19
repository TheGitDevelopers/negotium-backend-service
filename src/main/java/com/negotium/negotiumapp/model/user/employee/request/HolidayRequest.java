package com.negotium.negotiumapp.model.user.employee.request;

import com.negotium.negotiumapp.model.user.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @NotNull
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    public HolidayRequest() {
    }

    public HolidayRequest(Employee employee, @NotNull LocalDateTime startDate, @NotNull LocalDateTime endDate, @NotNull RequestStatus requestStatus) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestStatus = requestStatus;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HolidayRequest)) return false;
        HolidayRequest that = (HolidayRequest) o;
        return id.equals(that.id) &&
                employee.equals(that.employee) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                requestStatus == that.requestStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, startDate, endDate, requestStatus);
    }

    @Override
    public String toString() {
        return "HolidayRequest{" +
                "id=" + id +
                ", employee=" + employee +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", requestStatus=" + requestStatus +
                '}';
    }
}