package com.java.model;

public class ProjectTask {

	private Integer taskId;
	private String taskName;
	private String parentTask;
	private Integer priority;
	private String startDate;
	private String endDate;
	private String status;
	private Integer parentId;
	public ProjectTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectTask(Integer taskId, String taskName, String parentTask, Integer priority, String startDate, String endDate,
			String status, Integer parentId) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.parentTask = parentTask;
		this.priority = priority;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.parentId = parentId;
	}
	
	
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
