package com.sofka.project.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProjectObject{
    
// ------------------------------------------------------------- VARIABLES
@EqualsAndHashCode.Include
private Long id;

@NotBlank
@Size(max = 25)
private String name;


// ------------------------------------------------------------- CONSTRUCTORES

public ProjectObject() {
}

public ProjectObject(String name) {
    this.name = name;
}

public ProjectObject(Long id, String name) {
    this.id = id;
    this.name = name;
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    RoleObject other = (RoleObject) obj;
    if (id == null) {
        if (other.getId() != null)
            return false;
    } else if (!id.equals(other.getId()))
        return false;
    return true;
}


// ------------------------------------------------------------- GETTER-SETTERS

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}
}
