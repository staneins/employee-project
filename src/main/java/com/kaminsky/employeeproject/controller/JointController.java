package com.kaminsky.employeeproject.controller;

import com.kaminsky.employeeproject.entity.Department;
import com.kaminsky.employeeproject.entity.Employee;
import com.kaminsky.employeeproject.service.JointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/")
public class JointController {
    private final JointService jointService;

    public JointController(JointService jointService) {
        this.jointService = jointService;
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return jointService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = jointService.getEmployee(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        jointService.createEmployee(employee);
    }

    @PutMapping("{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        employee.setEmployeeId(id);
        jointService.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable Long id) {
        jointService.deleteEmployee(id);
    }

    @GetMapping("departments")
    public List<Department> getAllDepartments() {
        return jointService.getAllDepartments();
    }

    @GetMapping("departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = jointService.getDepartment(id);
        if (department.isPresent()) {
            return ResponseEntity.ok(department.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("departments")
    public void addDepartment(@RequestBody Department department) {
        jointService.createDepartment(department);
    }

    @PutMapping("departments/{id}")
    public void updateDepartment(@RequestBody Department department, @PathVariable Long id) {
        department.setDepartmentId(id);
        jointService.updateDepartment(department);
    }

    @DeleteMapping("departments/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        jointService.deleteDepartment(id);
    }
}
