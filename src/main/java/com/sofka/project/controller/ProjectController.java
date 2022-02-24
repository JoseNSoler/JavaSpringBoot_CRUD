package com.sofka.project.controller;

import java.util.*;

import com.sofka.project.dto.ProjectObject;
import com.sofka.project.dto.RoleObject;
import com.sofka.project.exception.CustomErrorException;
import com.sofka.project.model.Project;
import com.sofka.project.model.Role;
import com.sofka.project.service.ProjectServiceImpl;
import com.sofka.project.service.RoleServiceImpl;

import org.apache.logging.log4j.message.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/project"})
public class ProjectController {
    @Autowired
    ProjectServiceImpl projectServiceImpl;

    @Autowired
    private ModelMapper modelMapper;

    // POST method create project
    @PostMapping(value = {""})
    public ProjectObject createProject(@RequestBody @Validated ProjectObject projectDto, BindingResult errors){
        if(errors.hasErrors()){
            // throw exception error if encounter on validation process
            String errorCustom = CustomErrorException.getValidationMessage(errors.getFieldError());
            throw new CustomErrorException(HttpStatus.BAD_GATEWAY, errorCustom, errors);
        }
        else return projectServiceImpl.createProject(projectDto);
    }

    // Get all tables Project on DB
    @GetMapping(value = {""})
    public List<ProjectObject> getAllRole(){
        return projectServiceImpl.getAllProjectDto();
    }

    // Server answer GET /project/{id} 
    @GetMapping(path = "/{id}") 
    public Optional<ProjectObject> getRoleById(@PathVariable("id") Long id) {
        return projectServiceImpl.getByIdProjectDto(id);
    }

    //PUT method modify by id
    @PutMapping(path = "/{id}")
    public Project modifyProject(@PathVariable("id") Long id, @RequestBody @Validated ProjectObject projectDto, BindingResult errors){
        if(errors.hasErrors()){
            // throw exception error if encounter on validation process
            String errorCustom = CustomErrorException.getValidationMessage(errors.getFieldError());
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, errorCustom, errors);
        }
        return projectServiceImpl.modifyProjectById(id, projectDto);
    }

    // DELETE request with id of role
    @DeleteMapping(path = "/{id}")
    public Optional<ProjectObject> deleteProjectById(@PathVariable("id") Long id) {
        return projectServiceImpl.deleteByIdProjectDto(id);
    }
    /*

    // Get all tables Role on DB
    @GetMapping(value = {""})
    public List<RoleObject> getAllRole(){
        return roleServiceImpl.getAllRoleDto();
    }

    // Server answer GET /role/{id} 
    @GetMapping(path = "/{id}") 
    public Optional<RoleObject> getRoleById(@PathVariable("id") Long id) {
        return roleServiceImpl.getByIdRoleDto(id);
    }

    @PostMapping(value = {""})
    public RoleObject createRole(@RequestBody @Validated RoleObject roleDto, BindingResult errors){
        if(errors.hasErrors()){
            // throw exception error if encounter on validation process
            throw new CustomErrorException(
                new CustomErrorException().
                getValidationMessage(errors.getFieldError())
            );
        }
        else return roleServiceImpl.createRole(roleDto);
    }

    //PUT method modify by id
    @PutMapping(path = "/{id}")
    public Role modifyRole(@PathVariable("id") Long id, @RequestBody @Validated RoleObject roleObject, BindingResult errors){
        return roleServiceImpl.modifyById(id, roleObject);
    }

    // DELETE request with id of role
    @DeleteMapping(path = "/{id}")
    public Optional<RoleObject> deleteRoleById(@PathVariable("id") Long id) {
        return roleServiceImpl.deleteByIdRoleDto(id);
    }
    */
}
