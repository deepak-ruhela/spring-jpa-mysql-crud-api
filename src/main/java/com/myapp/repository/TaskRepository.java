package com.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myapp.model.Task;
import com.myapp.model.TaskType;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query(value = "SELECT COALESCE(MAX(taskId), 0) FROM Task")
	int getMaxId();

	@Query(value = "SELECT COALESCE(COUNT(taskId), 0) FROM Task")
	int getCount();

	List<Task> findByTaskType(TaskType taskType);

	List<Task> findByActive(boolean active);

	List<Task> findByActiveAndTaskType(boolean active, TaskType taskType);

	List<Task> findByNameIsStartingWithAllIgnoringCase(String name);

}