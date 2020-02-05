package com.negotium.negotiumapp.model.employee;

//@Mapper
public interface EmployeeMapper {

//    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
//
//    EmployeeDto toDTO(Employee entity);
//    Employee toEntity(EmployeeDto dto);

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
        employee.setEmployeeIndex(employee.getEmployeeIndex());
        return entity;
    }
}