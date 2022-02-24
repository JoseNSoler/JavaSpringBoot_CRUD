package com.sofka.project.service;

import com.sofka.project.dto.RoleObject;
import com.sofka.project.exception.CustomErrorException;
import com.sofka.project.model.Role;
import com.sofka.project.repository.IRoleJpaRepository;
import com.sofka.project.service.InterfaceService.IRoleService;

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
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleJpaRepository roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RoleObject> getAllRoleDto() {
        List<RoleObject> listRoleDto = roleRepo.findAll().stream()
                .map(role -> modelMapper.map(role, RoleObject.class))
                .collect(Collectors.toList());
        return listRoleDto;
    }

    // POST method by role
    @Override
    public RoleObject createRole(@Valid RoleObject role) {
        try {
            Role roleEntity = modelMapper.map(role, Role.class);
            roleEntity = roleRepo.save(roleEntity);
            return modelMapper.map(roleEntity, RoleObject.class);
        } catch (Exception e) {
            throw new CustomErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }

    }

    // GET method by id
    @Override
    public Optional<RoleObject> getByIdRoleDto(Long id) {
        try {
            Optional<Role> role = roleRepo.findById(id);

            if (role.isEmpty())
                throw new CustomErrorException(HttpStatus.NOT_FOUND,
                        String.format("_ERR: Role con ID=%s no fue posible encontrar en la Base de Datos", id));
            else {
                RoleObject roleEntity = modelMapper.map(role.get(), RoleObject.class);
                return Optional.of(roleEntity);
            }

        } catch (Exception e) {
            throw new CustomErrorException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

    @Override // PUT Modify content of role by id, or roleModel
    public Role modifyById(Long id, RoleObject roleObject) {
        Optional<Role> roleUpdate = roleRepo.findById(id);
    
        try {
            if (roleUpdate.isEmpty())
                throw new CustomErrorException(HttpStatus.NOT_FOUND,
                        String.format("_ERR: Role con ID=%s no fue posible encontrar y modificar en la Base de Datos",
                                id));
            else{
                RoleObject roleEntity = modelMapper.map(roleUpdate.get(), RoleObject.class);
                Role roleEntity2 = modelMapper.map(roleObject, Role.class);

                return roleUpdate.map(role -> {
                    role.setName(roleEntity2.getName() == null ? role.getName() : roleEntity2.getName());
                    
                    return roleRepo.save(role);
                }).orElseGet(()->{
                    roleEntity2.setId(id);
                    return roleRepo.save(roleEntity2);
                });
            }
        } catch (Exception e) {
            throw new CustomErrorException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

    // DELETE method by id - Returns the object deleted on DB
    @Override
    public Optional<RoleObject> deleteByIdRoleDto(Long id) {
        try {
            Optional<Role> role = roleRepo.findById(id);
            if (role.isEmpty())
                throw new CustomErrorException(HttpStatus.NOT_FOUND,
                        String.format("_ERR: Role con ID=%s no fue posible encontrar y eliminar en la Base de Datos",
                                id));
            else {
                roleRepo.deleteById(id);
                RoleObject roleEntity = modelMapper.map(role.get(), RoleObject.class);
                roleEntity.setName( "DELETED -->" + roleEntity.getName());
                return Optional.of(roleEntity);
            }
        } catch (Exception e) {
            // Default exception
            throw new CustomErrorException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

}
