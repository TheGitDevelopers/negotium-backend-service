package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.model.employee.Employee;
import com.negotium.negotiumapp.model.employee.EmployeeDto;
import com.negotium.negotiumapp.model.employee.EmployeeMapper;
import com.negotium.negotiumapp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    public final int idTEST = 2;
    public final String nameTEST = "Jimmy Doe";

    EmployeeService employeeService;
    @Mock
    EmployeeMapper employeeMapper;
    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(
                employeeRepository);
    }

    @Test
    void findAll() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee(), new Employee(), new Employee());

        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeDto> employeeDTOs = employeeService.findAll();

        //TODO: expected number depends on mysql database. Set H2 DB for testing purpose
        assertEquals(4, employeeDTOs.size());
    }

    @Test
    void findById() {
    }
}