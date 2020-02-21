package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.exception.DuplicateRequestIdException;
import com.negotium.negotiumapp.exception.HolidayRequestRemoveException;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequest;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequestDto;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequestMapper;
import com.negotium.negotiumapp.repository.HolidayRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HolidayRequestService {

    @Autowired
    private HolidayRequestRepository holidayRequestRepository;

    @Autowired
    public HolidayRequestService(HolidayRequestRepository holidayRequestRepository) {
        this.holidayRequestRepository = holidayRequestRepository;
    }

    public HolidayRequestDto addRequest(HolidayRequestDto holidayRequestDto) {
        Optional<HolidayRequest> findByRequestId = holidayRequestRepository.findById(holidayRequestDto.getId());
        findByRequestId.ifPresent(x -> {
            throw new DuplicateRequestIdException();
        });
        return mapAndSaveRequest(holidayRequestDto);
    }

    public HolidayRequestDto updateRequest(HolidayRequestDto holidayRequestDto) {
        Optional<HolidayRequest> findByRequestId = holidayRequestRepository.findById(holidayRequestDto.getId());
        findByRequestId.ifPresent(x -> {
            if (!x.equals(holidayRequestDto.getId()))
                throw new DuplicateRequestIdException();
        });
        return mapAndSaveRequest(holidayRequestDto);
    }

    private HolidayRequestDto mapAndSaveRequest(HolidayRequestDto holidayRequestDto){
        HolidayRequest holidayRequestEntity = HolidayRequestMapper.toEntity(holidayRequestDto);
        HolidayRequest savedRequest = holidayRequestRepository.save(holidayRequestEntity);
        return HolidayRequestMapper.toDto(savedRequest);
    }

    public void removeRequest(HolidayRequestDto holidayRequestDto){
        Optional<HolidayRequest> findByRequestId = holidayRequestRepository.findById(holidayRequestDto.getId());
        findByRequestId.ifPresentOrElse(x -> {
            holidayRequestRepository.delete(HolidayRequestMapper.toEntity(holidayRequestDto));
        }, () -> {
            throw new HolidayRequestRemoveException();
        });
    }

    public List<HolidayRequestDto> findAllRequestByEmployeeName(String name){
        return holidayRequestRepository.findAllByEmployeeName(name)
                .stream()
                .map(HolidayRequestMapper::toDto)
                .collect(Collectors.toList());
    }
}