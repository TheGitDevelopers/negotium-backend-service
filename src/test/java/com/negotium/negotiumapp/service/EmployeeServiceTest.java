package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.exception.DuplicatePersonIdNumberException;
import com.negotium.negotiumapp.model.user.employee.Employee;
import com.negotium.negotiumapp.model.user.employee.EmployeeDto;
import com.negotium.negotiumapp.model.user.employee.EmployeeMapper;
import com.negotium.negotiumapp.model.user.employee.details.EmpDetailsBuilder;
import com.negotium.negotiumapp.model.user.employee.details.EmployeeDetails;
import com.negotium.negotiumapp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class EmployeeServiceTest {
    public final Long idTEST = 2L;
    public final String nameTEST = "Jimmy Doe";
    public final String emailTEST = "jimmy@negotium.com";
    EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(
                employeeRepository);
    }

    @Test
    void should_save_employeeDTO() {
        //given
        Employee entity = getTestEmployee(nameTEST, idTEST, emailTEST);
        given(employeeRepository.save(any(Employee.class))).willReturn(entity);

        //when
        EmployeeDto savedDTO = employeeService.save(EmployeeMapper.toDto(entity));

        //then
        assertEquals(entity.getName(), savedDTO.getName());
        assertEquals(entity.getId(), savedDTO.getId());
        assertEquals(entity.getEmployeeIndex(), savedDTO.getEmployeeIndex());
    }

    @Test
    void should_update_employeeDTO() {
        //given
        Employee entity = getTestEmployee(nameTEST, idTEST, emailTEST);
        given(employeeRepository.save(any(Employee.class))).willReturn(entity);

        //when
        EmployeeDto savedDTO = employeeService.update(EmployeeMapper.toDto(entity));

        //then
        assertEquals(entity.getName(), savedDTO.getName());
        assertEquals(entity.getId(), savedDTO.getId());
        assertEquals(entity.getEmployeeIndex(), savedDTO.getEmployeeIndex());
    }

    @Test
    void should_find_employeeDTO_by_employeeIndex_and_throw_duplicatePerson_exception() throws DuplicatePersonIdNumberException {
        //given
        Employee entity = getTestEmployee(nameTEST, 666L, emailTEST);
        Employee entity2 = getTestEmployee((nameTEST + "2"), idTEST, emailTEST);
        given(employeeRepository.save(any(Employee.class))).willReturn(entity);
        given(employeeRepository.findByEmployeeIndex(anyInt())).willReturn(Optional.of(entity));

        //when
        EmployeeDto dto2 = EmployeeMapper.toDto(entity2);

        DuplicatePersonIdNumberException thrown = assertThrows(DuplicatePersonIdNumberException.class,
                () -> employeeService.update(dto2));
        EmployeeDto savedDTO = employeeService.update(dto2);

        //then
        assertEquals(entity.getEmployeeIndex(), savedDTO.getEmployeeIndex());
        assertNotEquals(entity.getId(), savedDTO.getId());
        assertEquals("User with this person id number is already exist", thrown.getMessage());
    }

    @Test
    void should_find_all_3_different_employeeDTOs_by_input_name() {
        //given
        List<Employee> employees = Arrays.asList(
                getTestEmployee(nameTEST, idTEST, emailTEST),
                getTestEmployee("Joanna Doe", 3L, "joanna@negotium.com"),
                getTestEmployee("Mark Doe", 4L, "marka@negotium.com"));
        given(employeeRepository.findAllByName(anyString())).willReturn(employees);

        //when
        List<EmployeeDto> found_dto = employeeService.findAllByName("Doe");
        then(employeeRepository).should(times(1)).findAllByName(anyString());

        //then
        assertEquals(3, found_dto.size());
        assertNotEquals(found_dto.get(0), found_dto.get(1));
        assertNotEquals(found_dto.get(1), found_dto.get(2));
        assertNotEquals(found_dto.get(0), found_dto.get(2));
    }

    @Test
    void should_find_all_employees() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee(), new Employee(), new Employee());
        given(employeeRepository.findAll()).willReturn(employees);

        List<EmployeeDto> employeeDTOs = employeeService.findAll();

        assertEquals(4, employeeDTOs.size());
    }

    @Test
    void should_find_employee_by_id() {
        Employee employee = getTestEmployee(nameTEST, idTEST, emailTEST);
        given(employeeRepository.findById(anyLong())).willReturn(Optional.of(employee));

        Optional<EmployeeDto> optional_dto = employeeService.findById(idTEST);

        then(employeeRepository).should(times(1)).findById(anyLong());

        optional_dto.ifPresent(dto -> {
            assertEquals(nameTEST, dto.getName());
            assertEquals(idTEST, dto.getId());
        });
    }

    private Employee getTestEmployee(String name, long id, String email) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setId(id);
        employee.setEmployeeIndex(777777);

        EmpDetailsBuilder builder = new EmpDetailsBuilder();
        builder.setEmail(email);
        builder.setContractType("FullTime");
        builder.setHoliday(22);
        builder.setSalary(30000);

        EmployeeDetails details = builder.getResult();

        employee.setEmployeeDetails(details);

        return employee;
    }
}