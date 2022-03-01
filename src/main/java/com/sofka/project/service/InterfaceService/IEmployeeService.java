package com.sofka.project.service.InterfaceService;

import javax.validation.Valid;

import com.sofka.project.dto.EmployeeObject;
import com.sofka.project.model.Employee;

public interface IEmployeeService {
    public EmployeeObject createEmployeeDto(EmployeeObject employeeObjectUser);
}
