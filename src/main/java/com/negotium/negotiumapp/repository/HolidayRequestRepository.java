package com.negotium.negotiumapp.repository;

import com.negotium.negotiumapp.model.user.employee.request.HolidayRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRequestRepository extends JpaRepository<HolidayRequest, Long> {
    List<HolidayRequest> findAllByEmployeeName(String name);
}
