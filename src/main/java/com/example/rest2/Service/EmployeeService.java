package com.example.rest2.Service;

import com.example.rest2.Data.models.Employee;
import com.example.rest2.Data.models.payloads.request.EmployeeRequest;
import com.example.rest2.Data.models.payloads.response.MessageResponse;
import com.example.rest2.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface EmployeeService
{
    MessageResponse createEmployee(EmployeeRequest employeeRequest);

    Optional<Employee> updateEmployee(Integer employeeId, EmployeeRequest employeeRequest) throws ResourceNotFoundException;
    void deleteEmployee(Integer employeeId) throws ResourceNotFoundException;
    Employee getASingleEmployee(Integer employeeId) throws ResourceNotFoundException;
    List<Employee> getAllEmployee();

}
