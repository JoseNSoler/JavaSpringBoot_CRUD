package com.sofka.project;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {
    
    // Validador entre clases Dto(copia validad de Rol) y clase Entity Rol para validacion intermedia
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
