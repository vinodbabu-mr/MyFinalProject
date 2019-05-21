package com.java.model;

import java.util.List;

public class ProjectTasks {

	private List<ProjectTask> tasks;
	private String status;
	private Integer code;

	public List<ProjectTask> getTask() {
		return tasks;
	}

	public void setTask(List<ProjectTask> tasks) {
		this.tasks = tasks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
