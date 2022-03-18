package com.nsl.trainning.simplecrud.taskUpdate;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.BeanUtils;
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
			throws EntityExistsException {

		Optional<TaskUpdate> taskUpdateByAuthorAndDate = taskUpdateRepository
				.findTaskUpdateByAuthorAndDate(taskUpdate.getAuthor(),
						taskUpdate.getLocalDate());

		if (taskUpdateByAuthorAndDate.isPresent()) {
			throw new EntityExistsException(
					"task update for author and date already exists: "
							+ taskUpdateByAuthorAndDate.toString());
		}

		taskUpdateRepository.save(taskUpdate);
	}

	private boolean taskUpdateWithAuthorAndDateExceptIdExists(
			TaskUpdate taskUpdate) {

		return taskUpdateRepository
				.findTaskUpdateByAuthorAndDateExceptId(taskUpdate.getAuthor(),
						taskUpdate.getLocalDate(), taskUpdate.getId())
				.isPresent();
	}

	public void updateTaskUpdate(TaskUpdate updatedTaskUpdate, Long id)
			throws NotFoundException, EntityExistsException {

		Optional<TaskUpdate> optional = taskUpdateRepository.findById(id);
		if (!optional.isPresent()) {
			throw new NotFoundException();
		}

		if(taskUpdateWithAuthorAndDateExceptIdExists(updatedTaskUpdate)) {
			throw new EntityExistsException();
		}
		
		TaskUpdate taskUpdate = optional.get();
		BeanUtils.copyProperties(updatedTaskUpdate, taskUpdate);
		if (taskUpdate.getId() == null) {
			taskUpdate.setId(id);
		}

		taskUpdateRepository.save(taskUpdate);
	}

	public void updateTaskUpdatePartial(TaskUpdate updatedTaskUpdate, Long id)
			throws NotFoundException, EntityExistsException {

		Optional<TaskUpdate> optional = taskUpdateRepository
				.findById(id);
		if (!optional.isPresent()) {
			throw new NotFoundException();
		}

		TaskUpdate taskUpdate = optional.get();
		// update only not null fields
		CustomUtil.copyNonNullProperties(updatedTaskUpdate, taskUpdate);

		// TODO: fix internal server error
		if (taskUpdateWithAuthorAndDateExceptIdExists(taskUpdate)) {
			throw new EntityExistsException();
		}

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
