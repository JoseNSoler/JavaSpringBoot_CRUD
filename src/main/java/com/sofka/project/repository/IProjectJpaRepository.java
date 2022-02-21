package com.sofka.project.repository;

import com.sofka.project.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz Projecto
 */
@Repository
public interface IProjectJpaRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
