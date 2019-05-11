package com.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.model.User;

@Repository
public interface UserRepository<B> extends JpaRepository<User,Long> {

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
	
	public List<User> findByFirstName(String firstName);
	
	public User findByUserId(Integer userId);
	public User findByProjectId(Integer projectId);
	public User findByTaskId(Integer taskId);
	
	@Query("SELECT u from User u order by user_id")
	public List<User> orderByUserId();
	
	@Query("SELECT u from User u order by first_name")
	public List<User> orderByFirstName();
	
	@Query("SELECT u from User u order by last_name")
	public List<User> orderByLastName();
	
	@Query("DELETE from User u where u.userId = :userId")
	public void deleteByUserId(@Param("userId") Integer userId);
	
//	@Query("SELECT b FROM Book b WHERE b.price >= :price")
//	public List<Book> findByPrice(@Param("price") double price);
	
}
