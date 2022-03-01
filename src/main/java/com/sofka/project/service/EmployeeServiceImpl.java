package com.sofka.project.service;

import com.sofka.project.dto.EmployeeObject;
import com.sofka.project.exception.CustomErrorException;
import com.sofka.project.model.Employee;
import com.sofka.project.model.Project;
import com.sofka.project.model.Role;
import com.sofka.project.repository.IEmployeeJpaRepository;
import com.sofka.project.repository.IProjectJpaRepository;
import com.sofka.project.repository.IRoleJpaRepository;
import com.sofka.project.service.InterfaceService.IEmployeeService;

import org.aspectj.apache.bcel.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Service
@Validated
public class EmployeeServiceImpl implements IEmployeeService{

    private IEmployeeJpaRepository employeeJpaRepository;
    private ModelMapper modelMapper;
    private IRoleJpaRepository roleJpaRepository;
    private IProjectJpaRepository projectJpaRepository;

    @Autowired
    public EmployeeServiceImpl(IEmployeeJpaRepository employeeJpaRepository, ModelMapper modelMapper,
        IRoleJpaRepository roleJpaRepository, IProjectJpaRepository projectJpaRepository){
            this.employeeJpaRepository = employeeJpaRepository;
            this.modelMapper = modelMapper;
            this.roleJpaRepository = roleJpaRepository;
            this.projectJpaRepository =  projectJpaRepository;
    }

    // Create object project
    @Override
    public EmployeeObject createEmployeeDto(EmployeeObject employeeObjectUser){
        
        try{
            List<Project> listProjectsUser = new ArrayList<>();
            Employee newEmployee = new Employee();
            EmployeeObject employeeObject = modelMapper.map(employeeObjectUser, EmployeeObject.class);
            Employee employee = modelMapper.map(employeeObject, Employee.class);

            System.out.println("///////////////////////////////////////////////////////////////");

            // Attr assignation to new object
            newEmployee.setFirstName(employee.getFirstName());
            newEmployee.setLastName(employee.getLastName());
            newEmployee.setEmployeeId(employee.getEmployeeId());



            // Role validation on dataBase
            Role roleUser = roleJpaRepository.findByName(employee.getRole().getName());
            if(roleUser == null){
                throw new CustomErrorException(HttpStatus.NOT_FOUND,
                    String.format("_ERR: Role con nombre=%s no fue posible encontrar en la Base de Datos",
                    employee.getRole().getName()));
            } else {
                newEmployee.setRole(roleUser);
            }

            // Project name validation list on data Base
            for(Project projectUser: employee.getProjects()){
                Project projectUserOptional = projectJpaRepository.findByName(projectUser.getName());
                if(projectUserOptional == null){
                    throw new CustomErrorException(HttpStatus.NOT_FOUND,
                        String.format("_ERR: Role con nombre=%s no fue posible encontrar en la Base de Datos",
                            projectUser.getName()));
                } else {
                    listProjectsUser.add(projectUserOptional);
                }
            }

            newEmployee.setProjects(listProjectsUser);

            System.out.println(roleUser.getName());

            // Save Employee
            
            var idEmployee = employeeJpaRepository.save(newEmployee).getId();


            employeeObject.setId(idEmployee);
            
            return employeeObject;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Create object
}
