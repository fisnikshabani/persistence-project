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

import java.util.Optional;


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
    }
}
