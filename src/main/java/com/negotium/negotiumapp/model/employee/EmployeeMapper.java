package com.negotium.negotiumapp.model.employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setPosition(employee.getPosition());
        dto.setContractType(employee.getContractType());
        dto.setNegotiumRole(employee.getNegotiumRole());
        dto.setEmployeeDetails(employee.getEmployeeDetails());
        return dto;
    }

    public static Employee toEntity(EmployeeDto employee){
        Employee entity = new Employee();
        entity.setId(employee.getId());
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setPosition(employee.getPosition());
        entity.setContractType(employee.getContractType());
        entity.setNegotiumRole(employee.getNegotiumRole());
        entity.setEmployeeDetails(employee.getEmployeeDetails());
        return entity;
    }
}
