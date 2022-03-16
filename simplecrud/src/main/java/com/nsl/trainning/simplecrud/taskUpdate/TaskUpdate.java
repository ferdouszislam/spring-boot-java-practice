package com.nsl.trainning.simplecrud.taskUpdate;

import java.time.LocalDate;
import java.util.List;

public class TaskUpdate {

	private Long id;
	private String author;
	private List<String> tasks;
	private LocalDate date;

	public TaskUpdate() {
	}

	public TaskUpdate(List<String> tasks, LocalDate date) {
		this.tasks = tasks;
		this.date = date;
	}

	public TaskUpdate(Long id, List<String> tasks, LocalDate date) {
		this.id = id;
		this.tasks = tasks;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<String> getTasks() {
		return tasks;
	}

	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}

	public LocalDate getLocalDate() {
		return date;
	}

	public void setLocalDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TaskUpdate [id=" + id + ", author=" + author + ", tasks=" + tasks + ", date=" + date + "]";
	}

}
