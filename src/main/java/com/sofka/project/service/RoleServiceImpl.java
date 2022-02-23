package com.sofka.project.service;

import com.sofka.project.dto.RoleObject;
import com.sofka.project.model.Role;
import com.sofka.project.repository.IRoleJpaRepository;
import com.sofka.project.service.IRoleService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class RoleServiceImpl implements IRoleService{
    
    @Autowired
    private IRoleJpaRepository roleRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<RoleObject> getAllRoleDto(){
        List<RoleObject> listRoleDto = roleRepo.findAll().stream()
        .map(role -> modelMapper.map(role, RoleObject.class))
        .collect(Collectors.toList());

        return listRoleDto;
    }


    @Override
    public RoleObject createRole(RoleObject role){
        Role roleEntity = modelMapper.map(role, Role.class);
        roleEntity = roleRepo.save(roleEntity);
        return modelMapper.map(roleEntity, RoleObject.class);
    }

    


}
