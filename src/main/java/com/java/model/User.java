package com.java.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "user")
@XmlRootElement
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "first_name")
	private String firstName;
    @Column(name = "last_name")
	private String lastName;
    @Column(name = "employee_id")
	private Integer employeeId;
    @Column(name = "project_id")
	private Integer projectId;
    @Column(name = "task_id")
	private Integer taskId;
	private static long count = 0;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String lastName, String firstName, Integer userId, Integer taskId, Integer employeeId,
			Integer projectId) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.userId = userId;
		this.taskId = taskId;
		this.employeeId = employeeId;
		this.projectId = projectId;
	}


	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", employeeId="
				+ employeeId + ", projectId=" + projectId + ", taskId=" + taskId + "]";
	}

}
