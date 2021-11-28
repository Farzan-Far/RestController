package com.example.rest2.Service;

import com.example.rest2.Data.models.Employee;
import com.example.rest2.Data.models.Repository.EmployeeRepository;
import com.example.rest2.Data.models.payloads.request.EmployeeRequest;
import com.example.rest2.Data.models.payloads.response.MessageResponse;
import com.example.rest2.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    EmployeeRepository repository;

    @Override
    public MessageResponse createEmployee(EmployeeRequest employeeRequest)
    {
       Employee newEmployee = new Employee();

       newEmployee.setFirstName(employeeRequest.getFirstName());
       newEmployee.setLastName(employeeRequest.getLastName());
       newEmployee.setPhoneNumber(employeeRequest.getPhoneNumber());
       newEmployee.setEmail(employeeRequest.getEmail());
       newEmployee.setSalary(employeeRequest.getSalary());
       newEmployee.setDepartment(employeeRequest.getDepartment());

       repository.save(newEmployee);
       return new MessageResponse("New Employee created successfully");
    }

    @Override
    public Optional<Employee> updateEmployee(Integer employeeId, EmployeeRequest employeeRequest)
            throws ResourceNotFoundException
    {
        Optional<Employee> employee = repository.findById(employeeId);
        if (employee.isPresent()){
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }

        else
        {
            employee.get().setFirstName(employeeRequest.getFirstName());
            employee.get().setLastName(employeeRequest.getLastName());
            employee.get().setPhoneNumber(employeeRequest.getPhoneNumber());
            employee.get().setEmail(employeeRequest.getEmail());
            employee.get().setSalary(employeeRequest.getSalary());
            employee.get().setDepartment(employeeRequest.getDepartment());
            repository.save(employee.get());
        }
        return employee;
    }

    @Override
    public void deleteEmployee(Integer employeeId)
            throws ResourceNotFoundException
    {
        if(repository.getById(employeeId).getId().equals(employeeId))
        {
            repository.deleteById(employeeId);
        }
        else throw new ResourceNotFoundException("Employee", "id", employeeId);
    }

    @Override
    public Employee getASingleEmployee(Integer employeeId)
            throws ResourceNotFoundException
    {
        return repository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    @Override
    public List<Employee> getAllEmployee()
    {
        return repository.findAll();

    }
}
