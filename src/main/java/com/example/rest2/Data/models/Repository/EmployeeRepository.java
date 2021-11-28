package com.example.rest2.Data.models.Repository;

import com.example.rest2.Data.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{

}
