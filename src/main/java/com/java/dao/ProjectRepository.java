package com.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.model.Project;

@Repository
public interface ProjectRepository<B> extends JpaRepository<Project,Long> {

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
	
	
	public Project findByProjectId(Integer projectId);
	
	@Query("SELECT u from Project u order by project_id")
	public List<Project> orderByProjectId();
	
	@Query("SELECT u from Project u order by start_date")
	public List<Project> orderByStartDate();
	
	@Query("SELECT u from Project u order by end_date")
	public List<Project> orderByEndDate();
	
	@Query("SELECT u from Project u order by priority")
	public List<Project> orderByPriority();
	
	@Query("SELECT u from Project u order by status")
	public List<Project> orderByStatus();
	
	@Query("DELETE from Project u where u.projectId = :projectId")
	public void deleteByProjectId(@Param("projectId") Integer projectId);
	
//	@Query("SELECT b FROM Book b WHERE b.price >= :price")
//	public List<Book> findByPrice(@Param("price") double price);
	
}
