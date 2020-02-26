package com.negotium.negotiumapp.service;

import com.negotium.negotiumapp.model.user.employee.Employee;
import com.negotium.negotiumapp.model.user.employee.details.EmpDetailsBuilder;
import com.negotium.negotiumapp.model.user.employee.details.EmployeeDetails;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequest;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequestDto;
import com.negotium.negotiumapp.model.user.employee.request.HolidayRequestMapper;
import com.negotium.negotiumapp.model.user.employee.request.RequestStatus;
import com.negotium.negotiumapp.repository.EmployeeRepository;
import com.negotium.negotiumapp.repository.HolidayRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class HolidayRequestServiceTest {
    public final Long idTEST = 2L;
    public final String nameTEST = "Jimmy Doe";
    public final String emailTEST = "jimmy@negotium.com";

    HolidayRequestService requestService;

    @Mock
    private HolidayRequestRepository holidayRequestRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        requestService = new HolidayRequestService(holidayRequestRepository, employeeRepository);
    }

    @Test
    void addRequest() {
//        given
        HolidayRequest entity = getTestRequest(nameTEST, idTEST, emailTEST);
        given(employeeRepository.findByEmployeeIndex(any(Integer.class)))
                .willReturn(Optional.of(entity.getEmployee()));
        given(holidayRequestRepository.save(any(HolidayRequest.class))).willReturn(entity);


        //when
        HolidayRequestDto savedDTO = requestService.addRequest(HolidayRequestMapper.toDto(entity));

        //then
        assertEquals(entity.getId(), savedDTO.getId());
        assertEquals(entity.getStartDate(), savedDTO.getStartDate());
        assertEquals(entity.getEndDate(), savedDTO.getEndDate());
    }

    @Test
    void updateRequest() {
        //given

        //when
        //then
    }

    @Test
    void removeRequest() {
    }

    @Test
    void findAllByEmployeeName() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    private HolidayRequest getTestRequest(String name, long id, String email) {
        Employee employee = getTestEmployee(name, id, email);

        HolidayRequest request = new HolidayRequest();
        request.setId(idTEST);
        request.setEmployee(employee);
        request.setStartDate(LocalDateTime.of(2020, 7, 3, 0, 0));
        request.setEndDate(LocalDateTime.of(2020, 7, 23, 0, 0));
        request.setRequestStatus(RequestStatus.WAITING);

        return request;
    }

    private Employee getTestEmployee(String name, long id, String email) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setId(id);
        employee.setEmployeeIndex(777777);

        EmpDetailsBuilder builder = new EmpDetailsBuilder();
        builder.setEmployeeEmail(email);
        builder.setContractType("FullTime");
        builder.setHoliday(24);
        builder.setSalary(30000);
        builder.setEmployee(employee);

        EmployeeDetails details = builder.getResult();

        employee.setEmployeeDetails(details);
        return employee;
    }
}