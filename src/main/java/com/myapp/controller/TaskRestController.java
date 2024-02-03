package com.myapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.config.CustomException;
import com.myapp.model.Task;
import com.myapp.repository.TaskRepository;

@RestController
@RequestMapping("/api/task")
public class TaskRestController {

	@Autowired
	private TaskRepository taskRepository;

	// http://localhost:8080/api/task
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> findAllTasks() {
		return taskRepository.findAll();
	}

	// http://localhost:8080/api/task/1
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> responseTask(@PathVariable(value = "id") Integer id) {

		Optional<Task> optional = taskRepository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		} else {
			return ResponseEntity.notFound().build();

		}

	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Task saveTask(@Validated @RequestBody Task task) {

		return taskRepository.save(task);
	}

	// {
//        "id": 3,
//        "name": "Updateto3",
//        "note": "noted3 task",
//        "taskType": "PRODUCT",
//        "active": true,
//        "createdAt": "2023-10-23T16:10:40.478987",
//        "updatedAt": "2023-10-23T16:10:40.478987"
//    }
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteTask(@PathVariable(value = "id") Integer id) {
		taskRepository.deleteById(id);
	}

	// http://localhost:8080/api/task/age/19
	@RequestMapping(value = "/age/{age}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> toCheck(@PathVariable(value = "age") Integer age) {
		if (age > 18) {
			return ResponseEntity.ok().body("Your vote is accepted");

		} else {

			throw new CustomException("Your age is not valid to caste  vote");

//            ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//            problemDetail.setTitle("Your vote is rejected");
//            problemDetail.setDetail("Your age is not valid to cat vote");
//            problemDetail.setType(URI.create("http://localhost:8080/api/errors"));
//            problemDetail.setProperty("host", "localhost");
//            problemDetail.setInstance(URI.create("http://localhost:8080/api/task/age/" + age));
//            problemDetail.setProperty("port", "8080");

//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail.toString());

		}

	}
}