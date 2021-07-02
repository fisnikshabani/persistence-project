package com.baeldung.lsd.persistence.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID().toString();

    private String name;

    private String description;

    private LocalDate dueDate;

    private TaskStatus status;

    @ManyToOne(optional = false)
    private Project project;

    @ManyToOne
    private User assignee;

    public Task(String name, String description, LocalDate dueDate, TaskStatus status, Project project) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = TaskStatus.TO_DO;
        this.project = project;
    }

    public Task() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;
        if (obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if(uuid == null){
            return other.uuid == null;
        } else return uuid.equals(other.uuid);
    }
}
