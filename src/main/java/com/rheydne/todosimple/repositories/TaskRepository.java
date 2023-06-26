package com.rheydne.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rheydne.todosimple.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
    // Método padrão SpringBoot
    List<Task> findByUser_Id(Long id); 

        // Método usado para querys personalizadas com "SQL"
    // @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id") 
    // List<Task> findByUser_Id(@Param("id") Long id);

        // Método para usar query padrão do sql
    // @Query(value = "SELET * FROM task t WHERE t.user_id = :id", nativeQuery = true)
    // List<Task> findByUser_Id(@Param("id") Long id);

}
