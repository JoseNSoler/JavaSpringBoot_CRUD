package com.sofka.project.service;

import java.util.*;

import javax.validation.Valid;

import com.sofka.project.dto.RoleObject;
import com.sofka.project.model.Role;


public interface IRoleService {
    public RoleObject createRole(@Valid RoleObject role);
    public List<RoleObject> getAllRoleDto();
}
