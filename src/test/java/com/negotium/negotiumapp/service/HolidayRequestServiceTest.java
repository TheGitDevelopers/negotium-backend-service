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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        HolidayRequestDto savedDTO = requestService.saveRequest(HolidayRequestMapper.toDto(entity));

        //then
        assertEquals(entity.getId(), savedDTO.getId());
        assertEquals(entity.getStartDate(), savedDTO.getStartDate());
        assertEquals(entity.getEndDate(), savedDTO.getEndDate());
    }

    @Test
    void updateRequest() {
//        given
        HolidayRequest entity = getTestRequest(nameTEST, idTEST, emailTEST);
        String originalStartDate = entity.getStartDate().format(DateTimeFormatter.BASIC_ISO_DATE);
        String originalEndDate = entity.getEndDate().format(DateTimeFormatter.BASIC_ISO_DATE);

        given(employeeRepository.findByEmployeeIndex(any(Integer.class)))
                .willReturn(Optional.of(entity.getEmployee()));
        given(holidayRequestRepository.save(any(HolidayRequest.class))).willReturn(entity);
        HolidayRequest updatedEntity = getTestRequest("New Name", idTEST, emailTEST);
        updatedEntity.setStartDate(LocalDateTime.now());
        updatedEntity.setEndDate((LocalDateTime.now()).plus(10, ChronoUnit.DAYS));

        given(employeeRepository.findByEmployeeIndex(any(Integer.class)))
                .willReturn(Optional.of(updatedEntity.getEmployee()));
        given(holidayRequestRepository.save(any(HolidayRequest.class))).willReturn(updatedEntity);
//        when
        HolidayRequestDto savedDTO = requestService.saveRequest(HolidayRequestMapper.toDto(entity));

        HolidayRequestDto updatedDTO = requestService.updateRequest(HolidayRequestMapper.toDto(updatedEntity));

//        that
        assertNotNull(updatedDTO);
        assertThat(savedDTO.getEmployee().getName(), equalTo(updatedDTO.getEmployee().getName()));
        assertThat(originalEndDate, not(equalTo(updatedDTO.getEndDate().format(DateTimeFormatter.BASIC_ISO_DATE))));
        assertThat(originalStartDate, not(equalTo(updatedDTO.getStartDate().format(DateTimeFormatter.BASIC_ISO_DATE))));
    }

    @Test
    void removeRequest() {
    }

    @Test
    void findAllByEmployeeName() {
        //given
        List<HolidayRequest> requests = getRequests();
        String searchfor = "immy";
        given(holidayRequestRepository.findAllByEmployeeName(any(String.class))).willReturn(
                requests.stream()
                        .filter(request ->
                                request.getEmployee()
                                        .getName().contains(searchfor))
                        .collect(Collectors.toList()));

        //when
        List<HolidayRequestDto> requestDtos = requestService
                .findAllByEmployeeName(searchfor);

        assertEquals(2, requestDtos.size());
    }

    @Test
    void findAll() {
        //given
        List<HolidayRequest> requests = getRequests();
        given(holidayRequestRepository.findAll()).willReturn(requests);
        //when
        List<HolidayRequestDto> requestDtos = requestService.findAll();

        //then
        assertEquals(4, requestDtos.size());
    }

    @Test
    void findById() {
        //given
        HolidayRequest request = getTestRequest(nameTEST, idTEST, emailTEST);
        given(holidayRequestRepository.findById(any(Long.class))).willReturn(Optional.of(request));

        //when
        Optional<HolidayRequestDto> optionalHolidayRequestDto = requestService.findById(idTEST);

        //then
        assertEquals(nameTEST, optionalHolidayRequestDto.get().getEmployee().getName());
        assertEquals(idTEST, optionalHolidayRequestDto.get().getEmployee().getId());
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

    private List<HolidayRequest> getRequests() {
        List<HolidayRequest> requests = new ArrayList<>();
        requests.add(getTestRequest(nameTEST, idTEST, emailTEST));
        requests.add(getTestRequest("Timmy", 5L, "timmy@negotium.com"));
        requests.add(getTestRequest("John", 3L, "johnny@negotium.com"));
        requests.add(getTestRequest("James", 4L, "james@negotium.com"));
        return requests;
    }
}