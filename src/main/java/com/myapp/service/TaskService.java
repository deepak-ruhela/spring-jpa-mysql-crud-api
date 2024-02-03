package com.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.model.Task;
import com.myapp.model.TaskType;
import com.myapp.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> getAllTasks() {

		return taskRepository.findAll();
	}

	public List<Task> getActiveTasks() {

		return taskRepository.findByActive(true);
	}

	public List<Task> getInactiveTasks() {

		return taskRepository.findByActive(false);
	}

	public List<Task> getTasksOfProduct() {

		return taskRepository.findByActiveAndTaskType(true, TaskType.PRODUCT);
	}

	public List<Task> getTasksOfBlog() {

		return taskRepository.findByActiveAndTaskType(true, TaskType.BLOG);
	}

	public Task saveTask(Task task) {

		if (task.getTaskId() == 0) {
			task.setTaskId(taskRepository.getMaxId() + 1);
			task.setActive(true);
		}

		taskRepository.save(task);

		return task;

	}

	public Task getOneTask(int id) {

		Optional<Task> optional = taskRepository.findById(id);

		return optional.get();
	}

	public void markTask(int id) {
		Optional<Task> optional = taskRepository.findById(id);
		Task task = optional.get();
		task.setActive(!task.isActive());
		taskRepository.save(task);

	}

	public void deleteTask(int id) {

		taskRepository.deleteById(id);

	}

	public int getMaxId() {
		return taskRepository.getMaxId() + 1;
	}

	public int getCount() {
		return taskRepository.getCount();
	}

}
