package com.negotium.negotiumapp.repository;

import com.negotium.negotiumapp.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Long, Employee> {
}
