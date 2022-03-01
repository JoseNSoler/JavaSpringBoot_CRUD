package com.sofka.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.*;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmployeeObject {

// ------------------------------ Variables
    @EqualsAndHashCode.Include
    private Long id;
    @NotBlank
    @Size(max = 10)
    private String employeeId;
    @NotBlank
    @Size(max = 25)
    private String firstName;
    @NotBlank
    @Size(max = 25)
    private String lastName;
    @NotNull
    private RoleObject role;
    private List<ProjectObject> projects = new ArrayList<ProjectObject>();


// --------------------------- Constructor
    public EmployeeObject(String firstName, String lastName, String employeeId, RoleObject role) {  
    this.firstName = firstName;
    this.lastName = lastName;
    this.employeeId = employeeId;
    this.role = role;
    }
    

    public EmployeeObject() {
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
        EmployeeObject other = (EmployeeObject) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

// --------- Getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleObject getRole() {
        return role;
    }

    public void setRole(RoleObject role) {
        this.role = role;
    }

    public List<ProjectObject> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectObject> projects) {
        this.projects = projects;
    }


    

}
