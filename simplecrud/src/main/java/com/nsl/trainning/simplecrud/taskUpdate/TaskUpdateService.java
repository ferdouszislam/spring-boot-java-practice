package com.nsl.trainning.simplecrud.taskUpdate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskUpdateService {

	private final TaskUpdateRepository taskUpdateRepository;

	@Autowired
	public TaskUpdateService(TaskUpdateRepository taskUpdateRepository) {
		this.taskUpdateRepository = taskUpdateRepository;
	}

	public List<TaskUpdate> getTaskUpdates() {
		return taskUpdateRepository.findAll();

		// return List.of(new TaskUpdate(1L, List.of("eat", "code", "sleep"),
		// LocalDate.of(2022, Month.MARCH, 16)))
	}

	public void createTaskUpdate(TaskUpdate taskUpdate) {
		taskUpdateRepository.save(taskUpdate);
	}

}
