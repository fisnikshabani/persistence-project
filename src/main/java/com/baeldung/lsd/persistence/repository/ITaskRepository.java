package com.baeldung.lsd.persistence.repository;

import com.baeldung.lsd.persistence.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITaskRepository extends PagingAndSortingRepository<Task, Long> {

    @Modifying
    @Query("delete Task t where t.status = com.baeldung.lsd.persistence.model.TaskStatus.DONE")
    int deleteCompletedTasks(); //modifying queries can return either void, int, or even Integer. However, returning anything other than these would result in an IllegalArgumentException.

}
