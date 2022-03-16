package com.nsl.trainning.simplecrud.taskUpdate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskUpdateRepository extends JpaRepository<TaskUpdate, Long> {

}
