package com.sofka.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sofka.project.model.Employee;
import com.sofka.project.model.Project;
import com.sofka.project.model.Role;
import com.sofka.project.repository.IEmployeeJpaRepository;
import com.sofka.project.repository.IProjectJpaRepository;
import com.sofka.project.repository.IRoleJpaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
// @AutoConfigureTestDatabase (connection = EmbeddedDatabaseConnection.H2)
public class EmployeeJpaRepositoyTest {
    @Autowired
    private IEmployeeJpaRepository repoEmpl;

    @Autowired
    private IRoleJpaRepository repoRole;

    @Autowired
    private IProjectJpaRepository repoProj;

    @Test
    public void saveEmployee() {

        Role admin = new Role("ROLE_ADMIN");
        Role dev = new Role("ROLE_DEV");

        admin = repoRole.save(admin);
        dev = repoRole.save(dev);

        Project proj1 = new Project("game1");
        Project proj2 = new Project("game2");
        Project proj3 = new Project("game3");

        proj1 = repoProj.save(proj1);
        proj2 = repoProj.save(proj2);
        proj3 = repoProj.save(proj3);

        Employee john = new Employee("John", "Smith", "empl123", dev);
        Employee Mavener = new Employee("Mavener", "ron", "empl124", admin);

        john.getProjects().add(proj1);
        john.getProjects().add(proj2);

        Mavener.getProjects().add(proj1);
        Mavener.getProjects().add(proj2);
        Mavener.getProjects().add(proj3);

        repoEmpl.save(john);
        repoEmpl.save(Mavener);

        repoEmpl.flush();

        Employee empl124 = repoEmpl.findByEmployeeId("empl124");
        assertEquals("Mavener", empl124.getFirstName());
        assertEquals(2, repoEmpl.findAll().size());
        assertEquals(admin, empl124.getRole());
    }
}
