package com.sofka.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.project.dto.EmployeeObject;
import com.sofka.project.dto.RoleObject;
import com.sofka.project.exception.CustomErrorException;
import com.sofka.project.model.Role;
import com.sofka.project.repository.IRoleJpaRepository;
import com.sofka.project.service.EmployeeServiceImpl;
import com.sofka.project.service.ProjectServiceImpl;
import com.sofka.project.service.RoleServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ch.qos.logback.core.boolex.Matcher;

import com.sofka.project.dto.ProjectObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.validation.constraints.*;

@Service
@Validated
public class ReqEmployeeWrapper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private static ModelMapper modelMapper;

    @Autowired
    private static IRoleJpaRepository roleRepo;



    /**
     * Convertir una cadena de cadenas json en un mapa String Obj
     * 
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convertir una cadena de cadenas json en un mapa String, String
     * 
     * @param json
     * @return
     */
    public static Map<String, String> jsonToMapString(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, RoleObject> jsonToMapRole(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T mapToBean(Map<String, Object> map, Class employee) throws Exception {

        Object object = employee.getClass();

        // Role;
        EmployeeObject mainEmployeeObject = new EmployeeObject();

        // No sé por qué esta oración no es válida ...
        // Field.setAccessible(clazz.getDeclaredFields(), true);

        map.forEach((k, v) -> {
            try {
                Field field = employee.getDeclaredField(k.toString());
                field.setAccessible(true);
                // Obtener el tipo de campo para juicio
                System.out.println(v);
                // Role
                if (field.getName().toString().equals("role")) {
                    String nameRole = "";
                    Long idRole = null;

                    String str = v.toString();
                    int startingIndex = str.indexOf("{");
                    int closingIndex = str.indexOf("}");
                    String result1 = str.substring(startingIndex + 1, closingIndex);
                    System.out.println(result1);

                    List<String> listArgs = Arrays.asList(result1.split(", ", -1));


                    for(String arg: listArgs){
                        System.out.println("--------");
                        List<String> listValues = Arrays.asList(arg.split("=", -1));
                        System.out.println(listValues.get(0).equals("name"));
                        if(listValues.get(0).equals("name")) nameRole = listValues.get(1);
                        
                        if(listValues.get(0).equals("id")) idRole = Long.parseLong(listValues.get(1));
                        
                    }
                    System.out.println(nameRole + " " + idRole);
                    System.out.println("///////////////////////////////////////////////////////////");

                    Role roleUsuario = new Role();
                    roleUsuario.setName(nameRole);
                    roleUsuario.setId(idRole);

                    System.out.println(roleRepo.exists(Example.of(roleUsuario)));
                    System.out.println("///////////////////////////////////////////////////////////-------");
                    try {
                        
                        Optional<Role> role = roleRepo.findById(idRole);
                        
                        if (role.isEmpty())
                            throw new CustomErrorException(HttpStatus.NOT_FOUND,
                                    String.format("_ERR: Role con ID=%s no fue posible encontrar en la Base de Datos", idRole));
                        else {
                            RoleObject roleEntity = modelMapper.map(role.get(), RoleObject.class);
                            mainEmployeeObject.setRole(roleEntity);
                        }
            
                    } catch (Exception e) {
                        throw new CustomErrorException(
                                HttpStatus.NOT_FOUND,
                                e.getMessage());
                    }        

                    // Role roleEntity = modelMapper.map(roleObject.get(), Role.class);
                    

                    System.out.println(mainEmployeeObject.getRole());

                    
                } 
                else if(field.getName().toString().equals("projects")){

                }
                else {
                    if (field.getName().toString().equals("employeeId")) mainEmployeeObject.setEmployeeId(v.toString());
                    if (field.getName().toString().equals("firstName")) mainEmployeeObject.setFirstName(v.toString());
                    if (field.getName().toString().equals("lastName")) mainEmployeeObject.setLastName(v.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return (T) object;
    }
}
