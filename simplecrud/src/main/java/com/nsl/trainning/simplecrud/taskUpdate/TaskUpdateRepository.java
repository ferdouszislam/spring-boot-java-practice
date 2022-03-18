package com.nsl.trainning.simplecrud.taskUpdate;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskUpdateRepository extends JpaRepository<TaskUpdate, Long> {

	@Query("SELECT tu FROM TaskUpdate tu WHERE tu.author = :author AND tu.date=:date")
	Optional<TaskUpdate> findTaskUpdateByAuthorAndDate(String author,
			LocalDate date);

	@Query("SELECT tu FROM TaskUpdate tu WHERE tu.author = :author AND tu.date = :date AND tu.id != :id")
	Optional<TaskUpdate> findTaskUpdateByAuthorAndDateExceptId(String author,
			LocalDate date, Long id);
}
