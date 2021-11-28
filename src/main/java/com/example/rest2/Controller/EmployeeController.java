package com.example.rest2.Controller;

import com.example.rest2.Data.models.Employee;
import com.example.rest2.Data.models.payloads.request.EmployeeRequest;
import com.example.rest2.Data.models.payloads.response.MessageResponse;
import com.example.rest2.Exception.ResourceNotFoundException;
import com.example.rest2.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee()
    {
        List<Employee> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id)
            throws ResourceNotFoundException
    {
        Employee employee = employeeService.getASingleEmployee(id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addEmployee(@RequestBody EmployeeRequest request)
    {
        MessageResponse newEmployee = employeeService.createEmployee(request);
        return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
    }
 /*   @PutMapping("/update/{id}")
    public ResponseEntity<MessageResponse> updateEmployee( @PathVariable Integer id, @RequestBody EmployeeRequest employee)
            throws ResourceNotFoundException
    {
        MessageResponse updateEmployee = employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id)
            throws ResourceNotFoundException
    {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
