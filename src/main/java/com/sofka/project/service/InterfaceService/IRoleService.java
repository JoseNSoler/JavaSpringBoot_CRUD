package com.sofka.project.service.InterfaceService;

import java.util.*;

import javax.validation.Valid;

import com.sofka.project.dto.RoleObject;
import com.sofka.project.model.Role;


public interface IRoleService {
    public RoleObject createRole(@Valid RoleObject role);
    public List<RoleObject> getAllRoleDto();
    public Optional<RoleObject> getByIdRoleDto(Long id);
    public Optional<RoleObject> deleteByIdRoleDto(Long id);
    public Role modifyById(Long id, @Valid RoleObject roleObject);
}
