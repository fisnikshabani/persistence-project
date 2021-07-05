package com.baeldung.lsd;

import com.baeldung.lsd.persistence.model.Task;
import com.baeldung.lsd.persistence.model.TaskStatus;
import com.baeldung.lsd.persistence.repository.ITaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.*;


@SpringBootApplication
public class PersistenceProjectApplication implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(PersistenceProjectApplication.class);

    @Autowired
    private ITaskRepository taskRepository;


    public static void main(String[] args) {
        SpringApplication.run(PersistenceProjectApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Pageable tasksFirstPage = PageRequest.of(0,2);
        Page<Task> tasksPage1 = taskRepository.findAll(tasksFirstPage);
        LOG.info("Page 1 of All Tasks: ");
        tasksPage1.forEach(task -> LOG.info(task.toString()));

        Pageable tasksSecondPage = PageRequest.of(1,2);
        Page<Task> tasksPage2 = taskRepository.findAll(tasksSecondPage);
        LOG.info("Page 2 of All Tasks");
        tasksPage2.forEach(task -> LOG.info(task.toString()));

        Sort sortByTaskNameDesc = Sort.by(Sort.Direction.DESC, "name");

        Iterable<Task> tasksSortedByNameDesc = taskRepository.findAll(sortByTaskNameDesc);
        LOG.info("All Tasks Sorted by name in descending order: ");
        tasksSortedByNameDesc.forEach(task -> LOG.info(task.toString()));

        Pageable tasksFirstPageSortedByNameDesc = PageRequest.of(0,2, Sort.by("name").descending());
        Page<Task> tasksPage1SortedByNameDsc = taskRepository.findAll(tasksFirstPageSortedByNameDesc);
        LOG.info("Page 1 of all tasks sorted by name in descending order: ");
        tasksPage1SortedByNameDsc.forEach(task -> LOG.info(task.toString()));


        Pageable twoTasksPagination = PageRequest.of(0,2);
        Page<Task> twoTasksAPage = taskRepository.findByStatus(TaskStatus.TO_DO, twoTasksPagination);

        LOG.info("All tasks by status paginated:\n {}", twoTasksAPage.getContent());
        LOG.info("Total No of Tasks:\n{}", twoTasksAPage.getTotalElements());
        LOG.info("Total pages of tasks: \n{}", twoTasksAPage.getTotalPages());

        System.out.println("********************** Paginating With Derived Methods Using Slice *********************");

        Pageable twoTasksPagination2 = PageRequest.of(0,2);
        Slice<Task> twoTasksASlice = taskRepository.findByNameLike("Task", twoTasksPagination2);
        LOG.info("All Tasks Sliced :\n {}", twoTasksASlice.getContent());
        LOG.info("Are there Slices Prior:\n {}", twoTasksASlice.hasPrevious());
        LOG.info("Are there Slices After:\n {}", twoTasksASlice.hasNext());

        System.out.println("********************** Paginating With Custom Queries *********************");

        Pageable pageTwo = PageRequest.of(1, 2);
        Page<Task> allTasksByNamePageTwo = taskRepository.allTasksByName("Task%", pageTwo);
        LOG.info("Page Two of All Tasks By Name:\n {}", allTasksByNamePageTwo.getContent());

    }
}
