package com.sofka.project.repository;

import com.sofka.project.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interfaz Rol
 */
@Repository
public interface IRoleJpaRepository extends JpaRepository<Role,Long>{
    Role findByName(String name);
}