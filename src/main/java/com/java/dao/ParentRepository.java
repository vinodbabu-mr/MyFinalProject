package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.Parent;

@Repository
public interface ParentRepository<B> extends JpaRepository<Parent,Long> {

	public Parent findByParentId(Integer parentId);
	
}
