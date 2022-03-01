package com.sofka.project.service;

import com.sofka.project.repository.IProjectJpaRepository;
import com.sofka.project.service.InterfaceService.IProjectService;


import com.sofka.project.dto.ProjectObject;
import com.sofka.project.dto.RoleObject;
import com.sofka.project.exception.CustomErrorException;
import com.sofka.project.model.Project;
import com.sofka.project.model.Role;
import com.sofka.project.repository.IRoleJpaRepository;
import com.sofka.project.service.InterfaceService.IRoleService;

import org.aspectj.apache.bcel.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.Valid;

@Service
@Validated
public class ProjectServiceImpl implements IProjectService{
    @Autowired
    private IProjectJpaRepository projectRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Create object project
    @Override
    public ProjectObject createProject(@Valid ProjectObject project){

            Project projectEntity = modelMapper.map(project, Project.class);
            projectEntity = projectRepo.save(projectEntity);
            return modelMapper.map(projectEntity, ProjectObject.class);

    }

    // GET all projects
    @Override
    public List<ProjectObject> getAllProjectDto() {
        List<ProjectObject> listRoleDto = projectRepo.findAll().stream()
                .map(role -> modelMapper.map(role, ProjectObject.class))
                .collect(Collectors.toList());
        return listRoleDto;
    }

    // GET method by id
    @Override
    public Optional<ProjectObject> getByIdProjectDto(Long id) {
        try {
            Optional<Project> project = projectRepo.findById(id);

            if (project.isEmpty())
                throw new CustomErrorException(HttpStatus.NOT_FOUND,
                        String.format("_ERR: Role con ID=%s no fue posible encontrar en la Base de Datos", id));
            else {
                ProjectObject projectEntity = modelMapper.map(project.get(), ProjectObject.class);
                return Optional.of(projectEntity);
            }

        } catch (Exception e) {
            throw new CustomErrorException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

    @Override // PUT Modify content of role by id, or roleModel
    public Project modifyProjectById(Long id, ProjectObject projectObject) {
        Optional<Project> projectUpdate = projectRepo.findById(id);
    
        try {
            if (projectUpdate.isEmpty())
                throw new CustomErrorException(HttpStatus.NOT_FOUND,
                        String.format("_ERR: Role con ID=%s no fue posible encontrar y modificar en la Base de Datos",
                                id));
            else{
                ProjectObject projectEntity = modelMapper.map(projectUpdate.get(), ProjectObject.class);
                Project projectEntity2 = modelMapper.map(projectObject, Project.class);

                return projectUpdate.map(project -> {
                    project.setName(projectEntity2.getName() == null ? project.getName() : projectEntity2.getName());
                    
                    return projectRepo.save(project);
                }).orElseGet(()->{
                    projectEntity2.setId(id);
                    return projectRepo.save(projectEntity2);
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
    public Optional<ProjectObject> deleteByIdProjectDto(Long id) {
        try {
            Optional<Project> project = projectRepo.findById(id);
            if (project.isEmpty())
                throw new CustomErrorException(HttpStatus.NOT_FOUND,
                        String.format("_ERR: Role con ID=%s no fue posible encontrar y eliminar en la Base de Datos",
                                id));
            else {
                projectRepo.deleteById(id);
                ProjectObject projectEntity = modelMapper.map(project.get(), ProjectObject.class);
                projectEntity.setName( "DELETED -->" + projectEntity.getName());
                return Optional.of(projectEntity);
            }
        } catch (Exception e) {
            // Default exception
            throw new CustomErrorException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }
}
