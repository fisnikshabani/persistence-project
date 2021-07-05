package com.baeldung.lsd.persistence.repository;

import com.baeldung.lsd.persistence.model.Task;
import com.baeldung.lsd.persistence.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITaskRepository extends PagingAndSortingRepository<Task, Long> {

    @Modifying
    @Query("delete Task t where t.status = com.baeldung.lsd.persistence.model.TaskStatus.DONE")
    int deleteCompletedTasks(); //modifying queries can return either void, int, or even Integer. However, returning anything other than these would result in an IllegalArgumentException.

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    Slice<Task> findByNameLike (String name, Pageable pageable);

    @Query("select t from Task t where t.name like ?1")
    Page<Task> allTasksByName(String name, Pageable pageable);

}
