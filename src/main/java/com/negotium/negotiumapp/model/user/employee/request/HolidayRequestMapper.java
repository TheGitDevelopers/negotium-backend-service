package com.negotium.negotiumapp.model.user.employee.request;

public class HolidayRequestMapper {

    public static HolidayRequestDto toDto(HolidayRequest holidayRequest){
        HolidayRequestDto dto = new HolidayRequestDto();
        dto.setId(holidayRequest.getId());
        dto.setEmployee(holidayRequest.getEmployee());
        dto.setStartDate(holidayRequest.getStartDate());
        dto.setEndDate(holidayRequest.getEndDate());
        dto.setRequestStatus(holidayRequest.getRequestStatus());
        return dto;
    }

    public static HolidayRequest toEntity(HolidayRequestDto holidayRequestDto){
        HolidayRequest entity = new HolidayRequest();
        entity.setId(holidayRequestDto.getId());
        entity.setEmployee(holidayRequestDto.getEmployee());
        entity.setStartDate(holidayRequestDto.getStartDate());
        entity.setEndDate(holidayRequestDto.getEndDate());
        entity.setRequestStatus(holidayRequestDto.getRequestStatus());
        return entity;
    }
}