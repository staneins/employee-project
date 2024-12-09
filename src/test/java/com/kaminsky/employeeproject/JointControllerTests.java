package com.kaminsky.employeeproject;
import com.kaminsky.employeeproject.controller.JointController;
import com.kaminsky.employeeproject.entity.Department;
import com.kaminsky.employeeproject.entity.Employee;
import com.kaminsky.employeeproject.service.JointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JointController.class)
public class JointControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JointService jointService;

    @BeforeEach
    public void setUp() {
        when(jointService.getAllEmployees()).thenReturn(new ArrayList<>());
        when(jointService.getAllDepartments()).thenReturn(new ArrayList<>());

        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPosition("Manager");
        employee.setSalary(75000);

        when(jointService.getEmployee(1L)).thenReturn(Optional.of(employee));

        Department department = new Department();
        department.setDepartmentId(1L);
        department.setName("HR");

        when(jointService.getDepartment(1L)).thenReturn(Optional.of(department));
    }

    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.employeeId").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.position").value("Manager"))
                .andExpect(jsonPath("$.salary").value(75000));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/1"))
                .andExpect(status().isOk());

        verify(jointService, times(1)).deleteEmployee(1L);
    }

    @Test
    public void testPostEmployee() throws Exception {
        String newEmployeeJson = """
                {
                    "firstName": "Jane",
                    "lastName": "Smith",
                    "position": "Developer",
                    "salary": 60000
                }
                """;

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newEmployeeJson))
                .andExpect(status().isOk());

        verify(jointService, times(1)).createEmployee(argThat(employee ->
                employee.getFirstName().equals("Jane") &&
                        employee.getLastName().equals("Smith")));
    }

    @Test
    public void testGetDepartments() throws Exception {
        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetDepartmentById() throws Exception {
        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.departmentId").value(1L))
                .andExpect(jsonPath("$.name").value("HR"));
    }

    @Test
    public void testPostDepartment() throws Exception {
        String newDepartmentJson = """
                {
                    "name": "HR"
                }
                """;

        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newDepartmentJson))
                .andExpect(status().isOk());

        verify(jointService, times(1)).createDepartment(argThat(department ->
                department.getName().equals("HR")));
    }

    @Test
    public void testPutDepartment() throws Exception {
        String updatedDepartmentJson = """
                {
                    "name": "HR"
                }
                """;

        mockMvc.perform(put("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedDepartmentJson))
                .andExpect(status().isOk());

        verify(jointService, times(1)).updateDepartment(argThat(department ->
                department.getName().equals("HR")));
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        mockMvc.perform(delete("/departments/1"))
                .andExpect(status().isOk());

        verify(jointService, times(1)).deleteDepartment(1L);
    }
}
