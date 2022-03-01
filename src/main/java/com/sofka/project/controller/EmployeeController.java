package com.sofka.project.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.sofka.project.dto.EmployeeObject;
import com.sofka.project.dto.ProjectObject;
import com.sofka.project.dto.RoleObject;
import com.sofka.project.exception.CustomErrorException;
import com.sofka.project.model.Employee;
import com.sofka.project.service.EmployeeServiceImpl;

import org.apache.logging.log4j.message.Message;
import org.apache.tomcat.util.json.JSONParser;
import org.json.*;
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
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping(value = {"/employee"})
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
    private ModelMapper modelMapper;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.employeeServiceImpl = employeeServiceImpl;
    }


    // Get all tables Project on DB

    
    @PostMapping(value = {""})
    public EmployeeObject createEmployee(@RequestBody @Valid EmployeeObject employeeDto, BindingResult errors){
        if(errors.hasErrors()){
            // throw exception error if encounter on validation process
            String errorCustom = CustomErrorException.getValidationMessage(errors.getFieldError());
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, errorCustom);
        } else return employeeServiceImpl.createEmployeeDto(employeeDto);
    }
}
