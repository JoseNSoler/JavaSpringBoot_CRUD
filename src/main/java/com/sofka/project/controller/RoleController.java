package com.sofka.project.controller;

import java.util.*;


import com.sofka.project.dto.RoleObject;
import com.sofka.project.exception.CustomErrorException;
import com.sofka.project.model.Role;
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
@RequestMapping(value = {"/role"})
public class RoleController {
    
    @Autowired
    RoleServiceImpl roleServiceImpl;

    @Autowired
    private ModelMapper modelMapper;

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
            String errorCustom = CustomErrorException.getValidationMessage(errors.getFieldError());
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, errorCustom, errors);
        }
        else return roleServiceImpl.createRole(roleDto);
    }

    //PUT method modify by id
    @PutMapping(path = "/{id}")
    public Role modifyRole(@PathVariable("id") Long id, @RequestBody @Validated RoleObject roleObject, BindingResult errors){
        if(errors.hasErrors()){
            // throw exception error if encounter on validation process
            String errorCustom = CustomErrorException.getValidationMessage(errors.getFieldError());
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, errorCustom, errors);
        }
        return roleServiceImpl.modifyById(id, roleObject);
    }

    // DELETE request with id of role
    @DeleteMapping(path = "/{id}")
    public Optional<RoleObject> deleteRoleById(@PathVariable("id") Long id) {
        return roleServiceImpl.deleteByIdRoleDto(id);
    }
}
