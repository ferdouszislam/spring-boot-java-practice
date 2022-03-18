package com.nsl.trainning.simplecrud.taskUpdate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.nsl.trainning.util.CustomUtil;

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

	public void updateTaskUpdate(TaskUpdate updatedTaskUpdate, Long id)
			throws NotFoundException {

		Optional<TaskUpdate> optional = taskUpdateRepository
				.findById(id);
		if (!optional.isPresent()) {
			throw new NotFoundException();
		}

		TaskUpdate taskUpdate = optional.get();

		CustomUtil.copyNonNullProperties(updatedTaskUpdate, taskUpdate);

		taskUpdateRepository.save(taskUpdate);
	}

	public void deleteTaskUpdate(Long id) {

		taskUpdateRepository.deleteById(id);
	}

	public TaskUpdate readTaskUpdate(Long id) throws NotFoundException {
		Optional<TaskUpdate> taskUpdate = taskUpdateRepository.findById(id);
		if (!taskUpdate.isPresent()) {
			throw new NotFoundException();
		}

		return taskUpdate.get();
	}

}
