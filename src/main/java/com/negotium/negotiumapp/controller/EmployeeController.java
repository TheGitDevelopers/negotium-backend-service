package com.negotium.negotiumapp.controller;

import com.negotium.negotiumapp.model.employee.EmployeeDto;
import com.negotium.negotiumapp.security.SecurityConstans;
import com.negotium.negotiumapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(SecurityConstans.API_EMPLOYEE)
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/findAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> findAll(@RequestParam(required = false) String name) {
        if (name != null) {
            return employeeService.findAllByName(name);
        }
        return employeeService.findAll();
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        if (employeeDto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User cannot have an id yet");
        }
        EmployeeDto savedEmployee = employeeService.save(employeeDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEmployee.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedEmployee);
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        if (!id.equals(employeeDto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Object id must match with id in resource path");
        }
        EmployeeDto updateEmployee = employeeService.update(employeeDto);
        return ResponseEntity.ok(updateEmployee);
    }
}