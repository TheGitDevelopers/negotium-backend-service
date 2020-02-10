package com.negotium.negotiumapp.model.user.employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmployeeIndex(employee.getEmployeeIndex());
        dto.setEmployeeDetails(employee.getEmployeeDetails());
        return dto;
    }

    public static Employee toEntity(EmployeeDto employee) {
        Employee entity = new Employee();
        entity.setId(employee.getId());
        entity.setName(employee.getName());
        entity.setEmployeeDetails(employee.getEmployeeDetails());
        entity.setEmployeeIndex(employee.getEmployeeIndex());
        return entity;
    }
}