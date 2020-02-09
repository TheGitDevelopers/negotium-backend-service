package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.exception.DuplicatePersonIdNumberException;
import com.negotium.negotiumapp.model.employee.Employee;
import com.negotium.negotiumapp.model.employee.EmployeeDto;
import com.negotium.negotiumapp.model.employee.EmployeeMapper;
import com.negotium.negotiumapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto save(EmployeeDto employeeDto) {
        Optional<Employee> findByEmployeeIndex =
                employeeRepository.findByEmployeeIndex(employeeDto.getEmployeeIndex());
        findByEmployeeIndex.ifPresent(x -> {
            throw new DuplicatePersonIdNumberException();
        });
        return mapAndSaveUser(employeeDto);
    }

    public EmployeeDto update(EmployeeDto employeeDto) {
        Optional<Employee> findByPersonIdNumber =
                employeeRepository.findByEmployeeIndex(employeeDto.getEmployeeIndex());
        findByPersonIdNumber.ifPresent(x -> {
            if ((!x.getId().equals(employeeDto.getId())))
                throw new DuplicatePersonIdNumberException();
        });
        return mapAndSaveUser(employeeDto);
    }

    private EmployeeDto mapAndSaveUser(EmployeeDto employeeDto) {
        Employee employeeEntity = EmployeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employeeEntity);
        return EmployeeMapper.toDto(savedEmployee);
    }

    public List<EmployeeDto> findAllByName(String name) {
        return employeeRepository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDto> findById(Long id) {
        return employeeRepository.findById(id).map(EmployeeMapper::toDto);
    }
}