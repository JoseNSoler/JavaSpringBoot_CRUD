package com.sofka.project.model;

import javax.persistence.*;
import java.util.*;

/**
 * Representa un empleado entidad
 */
@Entity
public class Employee {

    // ------------------------------------------------------------- VARIABLES

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 25, nullable = false)
    private String firstName;

    @Column(length = 25, nullable = false)
    private String lastName;

    @Column(length = 10, nullable = false, unique = true)
    private String employeeId;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_role")
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_project", 
             joinColumns = { @JoinColumn(name = "employee_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "project_id") })
    private List<Project> projects = new ArrayList<Project>();

    // ------------------------------------------------------------- CONSTRUCTORES

    public Employee() {
    }
 

    public Employee(String firstName, String lastName, String employeeId, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.role = role;
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
        Employee other = (Employee) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", id=" + id + ", lastName="
                + lastName + "]";
    }

    // ------------------------------------------------------------- GETTER-SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
