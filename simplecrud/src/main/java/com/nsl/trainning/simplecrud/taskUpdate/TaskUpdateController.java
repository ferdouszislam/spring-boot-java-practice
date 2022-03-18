package com.nsl.trainning.simplecrud.taskUpdate;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsl.trainning.util.ResponseMessage;

@RestController
@RequestMapping(path = "api/v1/taskUpdates")
public class TaskUpdateController {

	private final TaskUpdateService taskUpdateService;

	@Autowired
	public TaskUpdateController(TaskUpdateService taskUpdateService) {
		this.taskUpdateService = taskUpdateService;
	}

	@GetMapping
	public List<TaskUpdate> getTaskUpdates() {
		return taskUpdateService.getTaskUpdates();
	}

	@PostMapping
	public ResponseEntity<?> createTaskUpdate(
			@RequestBody TaskUpdate taskUpdate) {
		try {
			
			taskUpdateService.createTaskUpdate(taskUpdate);

		} catch (EntityExistsException e) {
			
			System.out.println("Exception occured while creating task update: "
					+ e.getMessage());

			String responseMessage = "task update for author: "
					+ taskUpdate.getAuthor() + " on "
					+ taskUpdate.getLocalDate().toString() + " already exists";

			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ResponseMessage(true, responseMessage));
		}

		return ResponseEntity.ok().body(taskUpdate);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> readTaskUpdate(@PathVariable Long id) {

		try {

			TaskUpdate taskUpdate = taskUpdateService.readTaskUpdate(id);
			return ResponseEntity.ok().body(taskUpdate);
		} catch (NotFoundException e) {

			System.out.println("Exception occured while reading taskUpdate: "
					+ e.getMessage());

			String responseMessage = "task update with id=" + id
					+ " does not exist";

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage(false, responseMessage));
		}
	}

	private ResponseEntity<?> updateTaskUpdateUtil(boolean partialUpdate,
			TaskUpdate taskUpdate, Long id) {
		try {

			if (partialUpdate) {
				taskUpdateService.updateTaskUpdatePartial(taskUpdate, id);
			} else {
				taskUpdateService.updateTaskUpdate(taskUpdate, id);
			}

		} catch (NotFoundException e) {

			System.out.println("Exception occured while updating taskUpdate: "
					+ e.getMessage());

			String responseMessage = "task update with id=" + id
					+ " does not exist";

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage(false, responseMessage));
		} catch (EntityExistsException e) {

			System.out.println("Exception occured while updating taskUpdate: "
					+ e.getMessage());

			String responseMessage = "task update for author: "
					+ taskUpdate.getAuthor() + " on "
					+ taskUpdate.getLocalDate().toString() + " already exists";

			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ResponseMessage(true, responseMessage));
		}

		String responseMessage = "taskUpdate with id=" + id
				+ " updated successfully";
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage(true, responseMessage));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTaskUpdate(
			@RequestBody TaskUpdate taskUpdate, @PathVariable Long id) {

		return updateTaskUpdateUtil(false, taskUpdate, id);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateTaskUpdatePartial(
			@RequestBody TaskUpdate taskUpdate, @PathVariable Long id) {

		return updateTaskUpdateUtil(true, taskUpdate, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTaskUpdate(@PathVariable Long id) {

		taskUpdateService.deleteTaskUpdate(id);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage(true, "task update with id=" + id
						+ " deleted successfully."));
	}
}
