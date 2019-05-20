package com.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.model.Task;

@Repository
public interface TaskRepository<B> extends JpaRepository<Task,Long> {

//	public void persist(T entity);
//	
//	public void update(T entity);
//	
//	public T findById(Id id);
//	
//	public void delete(T entity);
//	
//	public List<T> findAll();
//	
//	public void deleteAll();
	
	
	public Task findByTaskId(Integer taskId);
	
	@Query("SELECT u from Task u order by task_id")
	public List<Task> orderByTaskId();
	
	@Query("SELECT u from Task u order by start_date")
	public List<Task> orderByStartDate();
	
	@Query("SELECT u from Task u order by end_date")
	public List<Task> orderByEndDate();
	
	@Query("SELECT u from Task u order by priority")
	public List<Task> orderByPriority();
	
	@Query("SELECT u from Task u order by status")
	public List<Task> orderByStatus();
	
	@Query("DELETE from Task u where u.taskId = :taskId")
	public void deleteByTaskId(@Param("taskId") Integer taskId);
	
//	@Query("SELECT b FROM Book b WHERE b.price >= :price")
//	public List<Book> findByPrice(@Param("price") double price);
	
}
