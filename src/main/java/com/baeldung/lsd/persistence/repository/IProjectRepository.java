package com.baeldung.lsd.persistence.repository;

import com.baeldung.lsd.persistence.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IProjectRepository extends CrudRepository<Project, Long> {

    Optional<Project> findByCodeEquals(String code);

    int countByName(String name);

    Iterable<Project> findByNameContaining(String name);

    @Query("select p from Project p where p.name=?1 and p.description=?2")
    List<Project> findByNameAndDescriptionPositionalBind(String name, String description);

    @Query("select p from Project p where p.code in ?1")
    List<Project> findByCodeIn(Collection<String> codes);

    @Query("select p from Project p where p.description like %:keyword%")
    List<Project> findByDescriptionIsLike(@Param("keyword") String keyword);

    @Query("select p from Project p where p.description like CONCAT(:prefix, '%', :suffix)")
    List<Project> findByDescriptionWithPrefixAndSuffix(@Param("prefix") String prefix, @Param("suffix") String suffix);

    // Parameter Sanitization
    @Query("select p from Project p where p.description like :prefix%")
    List<Project> findByDescriptionWithPrefix(@Param("prefix") String prefix);

    @Query(value = "select * from Project p where LENGTH(p.description) < :length", nativeQuery = true)
    List<Project> findByDescriptionIsShorterThan(@Param("length") int len);


}
