package com.negotium.negotiumapp.model.employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmployeeIndex(entity.getEmployeeIndex());
        dto.setEmployeeDetails(entity.getEmployeeDetails());
        return dto;
    }

    public static Employee toEntity(EmployeeDto dto) {
        Employee entity = new Employee();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmployeeDetails(dto.getEmployeeDetails());
        entity.setEmployeeIndex(dto.getEmployeeIndex());
        return entity;
    }
}