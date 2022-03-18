package com.nsl.trainning.simplecrud.taskUpdate;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(name = "UniqueAuthorAndDate", columnNames = {"author",
				"date"})})
public class TaskUpdate {

	// TODO: find out what SequenceGenerator, GeneratedValue does?
	@Id
	@SequenceGenerator(name = "task_update_sequence", 
			sequenceName = "task_update_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "task_update_sequence")
	private Long id;

	private String author;

	@ElementCollection
	private List<String> tasks;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
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
		return "TaskUpdate [id=" + id + ", author=" + author + ", tasks="
				+ tasks + ", date=" + date + "]";
	}

}
