package com.sofka.project.controller;

import java.util.*;

import com.sofka.project.dto.RoleObject;
import com.sofka.project.exception.exceptionValidation;
import com.sofka.project.exception.exceptionError;
import com.sofka.project.exception.exceptionSubError;
import com.sofka.project.model.Role;
import com.sofka.project.service.RoleServiceImpl;

import org.apache.logging.log4j.message.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @PostMapping(value = {""})
    public ArrayList<String> creteRole(@RequestBody @Validated RoleObject roleDto, BindingResult errors){
        /*
        return new ResponseEntity<>(
          "Year of birth cannot be in the future", 
          HttpStatus.BAD_REQUEST);
        */

        if(errors.hasErrors()){
            System.out.println("/**************************************************************************************/-*/*-*/**/");
            System.out.println(exceptionValidation.getValidationMessage(errors));

            System.out.println("/**************************************************************************************/-*/*-*/**/");

             throw new exceptionSubError("esto es un error");
        }

        return exceptionValidation.getValidationMessage(errors);
        
    }
}
