package com.sofka.project.repository;

import com.sofka.project.model.Employee;
import com.sofka.project.model.Role;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeJpaRepository extends JpaRepository<Employee,Long>{
    // Seleccionar todos los usuarios donde tengan un mismo EmployeeId
    Employee findByEmployeeId(String employeeId);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByRole(Role role);
}
