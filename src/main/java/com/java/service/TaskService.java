package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.dao.ParentRepository;
import com.java.dao.TaskRepository;
import com.java.model.Parent;
import com.java.model.Parents;
import com.java.model.Task;
import com.java.model.Tasks;

@Service
@RestController
public class TaskService {

	@Autowired
	private TaskRepository<Task> TaskRepo;
	@Autowired
	private ParentRepository<Parent> parentRepo;
	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public Tasks getTasks() {

		List<Task> taskList = new ArrayList<Task>();
		Tasks response = new Tasks();
		taskList = TaskRepo.findAll();
		if (taskList != null) {
			response.setTask(taskList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/parents", method = RequestMethod.GET)
	public Parents getParents() {

		List<Parent> parentList = new ArrayList<Parent>();
		Parents response = new Parents();
		parentList = parentRepo.findAll();
		if (parentList != null) {
			response.setParent(parentList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}
	@RequestMapping(value = "/tasksById", method = RequestMethod.GET)
	public Tasks getTasksById() {

		List<Task> taskList = new ArrayList<Task>();
		Tasks response = new Tasks();
		taskList = TaskRepo.orderByTaskId();
		if (taskList != null) {
			response.setTask(taskList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/tasksByStartDate", method = RequestMethod.GET)
	public Tasks getTasksByStartDate() {

		List<Task> taskList = new ArrayList<Task>();
		Tasks response = new Tasks();
		taskList = TaskRepo.orderByStartDate();
		if (taskList != null) {
			response.setTask(taskList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/tasksByEndDate", method = RequestMethod.GET)
	public Tasks getTasksByEndDate() {

		List<Task> taskList = new ArrayList<Task>();
		Tasks response = new Tasks();
		taskList = TaskRepo.orderByEndDate();
		if (taskList != null) {
			response.setTask(taskList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}
	
	@RequestMapping(value = "/tasksByPriority", method = RequestMethod.GET)
	public Tasks getTasksByPriority() {

		List<Task> taskList = new ArrayList<Task>();
		Tasks response = new Tasks();
		taskList = TaskRepo.orderByPriority();
		if (taskList != null) {
			response.setTask(taskList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}
	
	@RequestMapping(value = "/tasksByStatus", method = RequestMethod.GET)
	public Tasks getTasksByStatus() {

		List<Task> taskList = new ArrayList<Task>();
		Tasks response = new Tasks();
		taskList = TaskRepo.orderByStatus();
		if (taskList != null) {
			response.setTask(taskList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
	public Tasks getTaskById(@PathVariable("id") Integer taskId) {
		Tasks response = new Tasks();
		List<Task> taskList = new ArrayList<Task>();
		Task task = TaskRepo.findByTaskId(taskId);
		if (task != null) {
			taskList.add(task);
			response.setTask(taskList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Tasks addTask(@RequestBody Task task) {
		Tasks response = new Tasks();
		task = TaskRepo.save(task);
		List<Task> taskLst = new ArrayList<>();
		if (task != null) {
			taskLst.add(task);
			response.setTask(taskLst);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}
	
	@RequestMapping(value = "/addParent", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Parent addParent(@RequestBody Parent parent) {
		parent = parentRepo.save(parent);
		return parent;
	}

	@RequestMapping(value = "/modifyTask", method = RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Tasks updateTask(@RequestBody Task task) {
		Tasks response = new Tasks();
		task = TaskRepo.save(task);
		List<Task> taskLst = new ArrayList<>();
		if (task != null) {
			taskLst.add(task);
			response.setTask(taskLst);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	//
	@RequestMapping(value = "/deleteTask/{id}", method = RequestMethod.DELETE)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Tasks deleteTask(@PathVariable("id") Integer TaskId) {
		Tasks response = new Tasks();
		try {
			Task task = TaskRepo.findByTaskId(TaskId);
			TaskRepo.delete(task);
			response.setStatus("Task Deleted Successfully");
			response.setCode(CODE_SUCCESS);
		} catch (Exception ex) {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
			ex.printStackTrace();
		}
		return response;
	}
}
