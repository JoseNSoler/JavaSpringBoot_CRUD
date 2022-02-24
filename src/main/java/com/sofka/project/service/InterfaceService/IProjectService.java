package com.sofka.project.service.InterfaceService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sofka.project.dto.ProjectObject;
import com.sofka.project.model.Project;

public interface IProjectService {
    public ProjectObject createProject(@Valid ProjectObject project);
    public List<ProjectObject> getAllProjectDto();
    public Optional<ProjectObject> getByIdProjectDto(Long id);
    public Project modifyProjectById(Long id, ProjectObject projectObject);
    public Optional<ProjectObject> deleteByIdProjectDto(Long id);
}
