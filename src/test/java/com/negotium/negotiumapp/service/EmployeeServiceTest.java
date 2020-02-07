package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.model.employee.Employee;
import com.negotium.negotiumapp.model.employee.EmployeeDto;
import com.negotium.negotiumapp.model.employee.EmployeeMapper;
import com.negotium.negotiumapp.model.employee.details.EmpDetailsBuilder;
import com.negotium.negotiumapp.model.employee.details.EmployeeDetails;
import com.negotium.negotiumapp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    public final Long idTEST = 2L;
    public final String nameTEST = "Jimmy Doe";
    public final String emailTEST = "jimmy@negotium.com";

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
        Employee employee = getTestEmployee(nameTEST, idTEST, emailTEST);

        given(employeeRepository.findById(anyLong())).willReturn(Optional.of(employee));

        Optional<EmployeeDto> optional_dto = employeeService.findById(idTEST);

        then(employeeRepository).should(times(1)).findById(anyLong());

        optional_dto.ifPresent(dto ->
                assertEquals(nameTEST, dto.getName()));
    }

    private Employee getTestEmployee(String name, long id, String email) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setId(id);
        employee.setEmployeeIndex(777777);

        EmpDetailsBuilder builder = new EmpDetailsBuilder();
        EmployeeDetails details = builder
                .email(email)
                .contractType("FullTime")
                .holiday(22)
                .salary(30000)
                .employee(employee)
                .build();

        employee.setEmployeeDetails(details);

        return employee;
    }
}