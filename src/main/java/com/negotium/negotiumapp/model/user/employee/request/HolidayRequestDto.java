package com.negotium.negotiumapp.model.user.employee.request;

import com.negotium.negotiumapp.model.user.employee.Employee;

import java.time.LocalDateTime;
import java.util.Objects;

public class HolidayRequestDto {

    private Long id;
    private Employee employee;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private RequestStatus requestStatus;

    public HolidayRequestDto() {
    }

    public HolidayRequestDto(Long id, Employee employee, LocalDateTime startDate, LocalDateTime endDate, RequestStatus requestStatus) {
        this.id = id;
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
        if (o == null || getClass() != o.getClass()) return false;
        HolidayRequestDto that = (HolidayRequestDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                requestStatus == that.requestStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, startDate, endDate, requestStatus);
    }
}