package com.negotium.negotiumapp.repository;

import com.negotium.negotiumapp.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeIndex(int employeeIndex);
    List<Employee> findAllByName(String name);
}