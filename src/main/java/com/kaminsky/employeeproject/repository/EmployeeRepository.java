package com.kaminsky.employeeproject.repository;

import com.kaminsky.employeeproject.entity.Employee;
import com.kaminsky.employeeproject.projections.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<EmployeeProjection> getFullName(Long id);
    List<EmployeeProjection> getPosition(Long id);
    List<EmployeeProjection> getDepartment(Long id);
}
