package com.nsl.trainning.simplecrud.taskUpdate;

import java.util.List;
import java.util.Optional;

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
	}

	public void createTaskUpdate(TaskUpdate taskUpdate)
			throws IllegalStateException {

		Optional<TaskUpdate> taskUpdateByAuthorAndDate = taskUpdateRepository
				.findTaskUpdateByAuthorAndDate(taskUpdate.getAuthor(),
						taskUpdate.getLocalDate());

		if (taskUpdateByAuthorAndDate.isPresent()) {
			throw new IllegalStateException(
					"task update for author and date already exists: "
							+ taskUpdateByAuthorAndDate.toString());
		}

		taskUpdateRepository.save(taskUpdate);
	}

}
