package com.baeldung.lsd;

import com.baeldung.lsd.persistence.model.Project;
import com.baeldung.lsd.persistence.model.Task;
import com.baeldung.lsd.persistence.repository.IProjectRepository;
import com.baeldung.lsd.persistence.repository.ITaskRepository;
import com.baeldung.lsd.persistence.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@SpringBootApplication
public class PersistenceProjectApplication implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(PersistenceProjectApplication.class);

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IUserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(PersistenceProjectApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("Starting Spring Boot application");

        Iterable<Project> allProjects = projectRepository.findAll();
        LOG.info("All Projects:\n{}", allProjects);

        Optional<Task> project1 = taskRepository.findById(1L);
        LOG.info("Task by id 1: \n{}", project1);

        long noOfUsers = userRepository.count();
        LOG.info("Number of users: \n{}", noOfUsers);

        Optional<Project> P1 = projectRepository.findByCodeEquals("P1");
        LOG.info("Project with code P1: \n", P1);

        int projectCount = projectRepository.countByName("Project 1");
        LOG.info("Number of projects with name: 'Project 1':\n", projectCount);

        List<Project> projectList1 = projectRepository.findByNameAndDescriptionPositionalBind("Project 3", "About Project 3");
        LOG.info("find Project 3 using positional parameters :\n{}", projectList1);

        List<Project> projectList3 = projectRepository.findByCodeIn(Set.of("P2", "P3"));
        LOG.info("find Project 2 and Project 3 using IN clause:\n{}", projectList3);

        List<Project> projectList4 = projectRepository.findByDescriptionIsLike("About");
        LOG.info("find Projects containing 'About' using LIKE clause:\n{}", projectList4);

        List<Project> projectList5 = projectRepository.findByDescriptionWithPrefixAndSuffix("About", "3");
        LOG.info("find Project 3 using prefix and suffix in LIKE clause:\n{}", projectList5);

       List<Project> projectList6 = projectRepository.findByDescriptionIsShorterThan(16);
        LOG.info("find Projects with short descriptions using Native query:\n{}", projectList6);

        List<Project> projectList7 = projectRepository.findByDescriptionWithPrefix("%");
        LOG.info("find all Projects by passing '%' to LIKE clause:\n{}", projectList7);


    }
}
