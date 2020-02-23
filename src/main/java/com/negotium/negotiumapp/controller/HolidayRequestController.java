package com.negotium.negotiumapp.controller;

import com.negotium.negotiumapp.model.user.employee.request.HolidayRequestDto;
import com.negotium.negotiumapp.service.HolidayRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/holidayRequest")
public class HolidayRequestController {

    @Autowired
    private HolidayRequestService holidayRequestService;

    public HolidayRequestController(HolidayRequestService holidayRequestService) {
        this.holidayRequestService = holidayRequestService;
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HolidayRequestDto> save(@RequestBody HolidayRequestDto holidayRequestDto) {
        HolidayRequestDto savedRequest = holidayRequestService.addRequest(holidayRequestDto);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping(path = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<HolidayRequestDto> findByEmployeeName(@RequestParam(required = false) String employeeName) {
        if(employeeName != null){
            return holidayRequestService.findAllByEmployeeName(employeeName);
        }
      return  holidayRequestService.findAll();
    }


    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HolidayRequestDto> findById(@PathVariable Long id){
        return holidayRequestService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HolidayRequestDto> update(@PathVariable Long id, @RequestBody HolidayRequestDto holidayRequestDto){
      if(!id.equals(holidayRequestDto.getId())){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Object id must match with id in resource path");
      }
        HolidayRequestDto updateRequest = holidayRequestService.updateRequest(holidayRequestDto);
      return ResponseEntity.ok(updateRequest);
    }
}