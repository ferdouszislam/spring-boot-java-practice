package com.nsl.trainning.simplecrud.taskUpdate;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class TaskUpdateService {

	@GetMapping
	public List<TaskUpdate> getTaskUpdates() {
		return List.of(new TaskUpdate(1L, List.of("eat", "code", "sleep"),
				LocalDate.of(2022, Month.MARCH, 16)));
	}
}
