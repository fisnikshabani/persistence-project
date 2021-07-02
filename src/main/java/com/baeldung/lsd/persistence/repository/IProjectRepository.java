package com.baeldung.lsd.persistence.repository;

import com.baeldung.lsd.persistence.model.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IProjectRepository extends CrudRepository<Project, Long> {

    Optional<Project> findByCodeEquals(String code);

    int countByName(String name);

    Iterable<Project> findByNameContaining(String name);
}
