package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.exception.DuplicateRequestIdException;
import com.negotium.negotiumapp.exception.HolidayRequestRemoveException;
import com.negotium.negotiumapp.model.user.employee.Employee;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequest;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequestDto;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequestMapper;
import com.negotium.negotiumapp.repository.EmployeeRepository;
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
    private EmployeeRepository employeeRepository;

    @Autowired
    public HolidayRequestService(HolidayRequestRepository holidayRequestRepository, EmployeeRepository employeeRepository) {
        this.holidayRequestRepository = holidayRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    public HolidayRequestDto addRequest(HolidayRequestDto holidayRequestDto) {
        Optional<HolidayRequest> findByRequestId = holidayRequestRepository.findById(holidayRequestDto.getId());
        findByRequestId.ifPresent(x -> {
            throw new DuplicateRequestIdException("Request with this ID is already exist");
        });
        Optional<Employee> findOneEmployeeByName = employeeRepository
                .findByEmployeeIndex(holidayRequestDto.getEmployee().getEmployeeIndex());
        findOneEmployeeByName.ifPresentOrElse(
                holidayRequestDto::setEmployee,
                () -> {
                    throw new NullPointerException("Employee not found");
                }
        );

        return mapAndSaveRequest(holidayRequestDto);
    }

    public HolidayRequestDto updateRequest(HolidayRequestDto holidayRequestDto) {
        Optional<HolidayRequest> findByRequestId = holidayRequestRepository.findById(holidayRequestDto.getId());
        findByRequestId.ifPresent(x -> {
            if (!(x.getId()).equals(holidayRequestDto.getId()))
                throw new DuplicateRequestIdException("Request with this ID is already exist");
        });
        return mapAndSaveRequest(holidayRequestDto);
    }

    private HolidayRequestDto mapAndSaveRequest(HolidayRequestDto holidayRequestDto) {
        HolidayRequest holidayRequestEntity = HolidayRequestMapper.toEntity(holidayRequestDto);
        HolidayRequest savedRequest = holidayRequestRepository.save(holidayRequestEntity);
        return HolidayRequestMapper.toDto(savedRequest);
    }

    public void removeRequest(HolidayRequestDto holidayRequestDto) {
        Optional<HolidayRequest> findByRequestId = holidayRequestRepository.findById(holidayRequestDto.getId());
        findByRequestId.ifPresentOrElse(x ->
                        holidayRequestRepository
                                .delete(HolidayRequestMapper
                                        .toEntity(holidayRequestDto)),
                () -> {
                    throw new HolidayRequestRemoveException(
                            "The request could not be deleted. Please try again later");
                });
    }

    public List<HolidayRequestDto> findAllByEmployeeName(String name) {
        return holidayRequestRepository.findAllByEmployeeName(name)
                .stream()
                .map(HolidayRequestMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<HolidayRequestDto> findAll() {
        return holidayRequestRepository.findAll()
                .stream()
                .map(HolidayRequestMapper::toDto)
                .collect(Collectors.toList());
    }


    public Optional<HolidayRequestDto> findById(Long id) {
        return holidayRequestRepository.findById(id).map(HolidayRequestMapper::toDto);
    }
}