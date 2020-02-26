package com.negotium.negotiumapp.model.user.employee.request;

public class HolidayRequestMapper {

    public static HolidayRequestDto toDto(HolidayRequest entity) {
        HolidayRequestDto dto = new HolidayRequestDto();
        dto.setId(entity.getId());
        dto.setEmployee(entity.getEmployee());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setRequestStatus(entity.getRequestStatus());
        return dto;
    }

    public static HolidayRequest toEntity(HolidayRequestDto dto) {
        HolidayRequest entity = new HolidayRequest();
        entity.setId(dto.getId());
        entity.setEmployee(dto.getEmployee());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setRequestStatus(dto.getRequestStatus());
        return entity;
    }
}