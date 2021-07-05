package com.baeldung.lsd.persistence.repository;

import com.baeldung.lsd.persistence.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {

    @Modifying
    @Query(value = "ALTER TABLE User ADD COLUMN active int(1) NOT NULL DEFAULT 1", nativeQuery = true)
    void addActiveColumn();
}
