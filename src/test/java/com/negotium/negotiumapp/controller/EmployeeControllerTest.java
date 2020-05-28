package com.negotium.negotiumapp.controller;

import com.negotium.negotiumapp.model.employee.Employee;
import com.negotium.negotiumapp.model.employee.EmployeeDto;
import com.negotium.negotiumapp.model.employee.EmployeeMapper;
import com.negotium.negotiumapp.model.employee.details.EmpDetailsBuilder;
import com.negotium.negotiumapp.model.employee.details.EmployeeDetails;
import com.negotium.negotiumapp.security.SecurityConstans;
import com.negotium.negotiumapp.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeControllerTest extends AbstractRestControllerTest {
    public final Long idTEST = 2L;
    public final String nameTEST = "Jimmy Doe";
    public final String emailTEST = "jimmy@negotium.com";

    @Mock
    EmployeeService service;

    @InjectMocks
    EmployeeController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void should_find_all_employees() throws Exception {
        //given
        List<EmployeeDto> dtos = Arrays.asList(
                EmployeeMapper.toDto(getTestEmployee(nameTEST, idTEST, emailTEST)),
                EmployeeMapper.toDto(getTestEmployee("Joanna Doe", 3L, "joanna@negotium.com")),
                EmployeeMapper.toDto(getTestEmployee("Mark Doe", 4L, "marka@negotium.com")));

        given(service.findAll()).willReturn(dtos);

        //when
        //then
        mockMvc.perform(
                get(SecurityConstans.API_EMPLOYEE + "/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].name", is("Joanna Doe")));
    }

    @Test
    void should_save_employee() throws Exception {
        //given
        EmployeeDto dto = EmployeeMapper.toDto(getTestEmployee(nameTEST, 365L, emailTEST));
        dto.setId(null);

        given(service.save(any(EmployeeDto.class))).willReturn(dto);

        //when
        //then
        mockMvc.perform(
                post(SecurityConstans.API_EMPLOYEE + "/save")
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(nameTEST)))
                .andExpect(jsonPath("$.employeeDetails.email", equalTo(emailTEST)));
    }

    @Test
    void should_find_employee_by_Id() throws Exception {
        //given
        EmployeeDto dto = EmployeeMapper.toDto(getTestEmployee(nameTEST, idTEST, emailTEST));

        given(service.findById(anyLong())).willReturn(Optional.of(dto));

        //when
        //then

        mockMvc.perform(
                get(SecurityConstans.API_EMPLOYEE + "/" + idTEST.intValue())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(nameTEST)))
                .andExpect(jsonPath("$.employeeDetails.salary", equalTo(30000.0)))
                .andExpect(jsonPath("$.employeeDetails.contractType", equalTo("FullTime")));
    }

    @Test
    void should_update_employee() throws Exception {
        //given
        EmployeeDto dto = EmployeeMapper.toDto(getTestEmployee(nameTEST, 365L, emailTEST));
        dto.setName("Jane Doe");
        dto.setEmployeeIndex(666);

        given(service.update(any(EmployeeDto.class))).willReturn(dto);

        //when
        //then
        mockMvc.perform(
                put(SecurityConstans.API_EMPLOYEE + "/365")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Jane Doe")))
                .andExpect(jsonPath("$.name", not(equalTo(nameTEST))))
                .andExpect(jsonPath("$.employeeIndex", equalTo(666)))
                .andExpect(jsonPath("$.employeeDetails.email", equalTo(emailTEST)));
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
        builder.setEmployee(employee);

        EmployeeDetails details = builder.getResult();

        employee.setEmployeeDetails(details);

        return employee;
    }
}