package com.nsl.trainning.simplecrud.taskUpdate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
