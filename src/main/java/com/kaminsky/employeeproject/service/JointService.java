package com.kaminsky.employeeproject.service;

import com.kaminsky.employeeproject.entity.Department;
import com.kaminsky.employeeproject.entity.Employee;
import com.kaminsky.employeeproject.repository.DepartmentRepository;
import com.kaminsky.employeeproject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JointService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public JointService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public void createDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Department> getDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department;
    }

    public Optional<Employee> getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void updateDepartment(Department department) {
        if (departmentRepository.existsById(department.getDepartmentId())) {
            departmentRepository.save(department);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void updateEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getEmployeeId())) {
            employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
