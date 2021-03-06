package com.java.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.dao.ParentRepository;
import com.java.dao.TaskRepository;
import com.java.model.Parent;
import com.java.model.Parents;
import com.java.model.ProjectTask;
import com.java.model.ProjectTasks;
import com.java.model.Task;
import com.java.model.Tasks;
import com.java.rest.RestfulApplication;

import org.junit.Assert;
import org.junit.Before;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RestfulApplication.class)
@SpringBootTest
@ActiveProfiles("CloudX")
public class TaskServiceTest {

	@Autowired
	private TaskService taskService;

	@MockBean
	private TaskRepository<Task> taskRepo;
	@MockBean
	private ParentRepository<Parent> parentRepo;
	
	Task task;
	ProjectTask projectTask;
	Parent parent;
	
	List<Task> taskList  = new ArrayList<Task>();
	Tasks response;
	
	List<Parent> parentList = new ArrayList<Parent>();
	Parents res;
	
	List<Object[]> objList = new ArrayList<>();
	List<ProjectTask> prjTaskList  = new ArrayList<ProjectTask>();
	ProjectTasks resp;
	
	@Before
	public void setUp() throws ParseException {
		task = new Task(1,"CONGO", "2019-01-01","2019-12-31",10, "IN PROGRESS", 1,2);
		projectTask = new ProjectTask(1,"Interview","Recruiting",10,"2019-01-01","2019-12-31","IN PROGRESS",1);
		parent = new Parent("Invoicing",0);
		taskList.add(task);
		prjTaskList.add(projectTask);
		parentList.add(parent);
		Object[] obj = {1,"Interview","Recruit","2019-01-01","2019-12-31",15,"COMPLETED",4};
		objList.add(obj);
		response = new Tasks();
		response.setTask(taskList);
		response.setStatus("success");
		response.setCode(100);
		resp = new ProjectTasks();
		resp.setTask(prjTaskList);
		resp.setStatus("success");
		resp.setCode(100);
		res = new Parents();
		res.setParent(parentList);
		res.setStatus("success");
		res.setCode(100);
	}

	@Test
	public void testGetTasks() {
		Mockito.when(taskRepo.findAll()).thenReturn(taskList);
		Tasks tasks = taskService.getTasks();
		List<Task> taskLst = tasks.getTask();
		Assert.assertEquals(taskLst.get(0).getTaskName(),taskList.get(0).getTaskName());
		Assert.assertEquals(taskLst.get(0).getStartDate(),taskList.get(0).getStartDate());
		Assert.assertEquals(taskLst.get(0).getEndDate(),taskList.get(0).getEndDate());
		Assert.assertEquals(taskLst.get(0).getPriority(),taskList.get(0).getPriority());
		Assert.assertEquals(taskLst.get(0).getStatus(),taskList.get(0).getStatus());
		Assert.assertEquals(taskLst.get(0).getProjectId(),taskList.get(0).getProjectId());
		Assert.assertEquals(tasks.getStatus(),"success");
		Assert.assertEquals(tasks.getCode().longValue(),100L);
		Assert.assertNotNull(task.toString());
	}
	
	@Test
	public void testGetParentTasks() {
		Mockito.when(parentRepo.findAll()).thenReturn(parentList);
		Parents parents = taskService.getParents();
		List<Parent> taskLst = parents.getParent();
		Assert.assertEquals(taskLst.get(0).getName(),parentList.get(0).getName());
		Assert.assertEquals(taskLst.get(0).getParentId(),parentList.get(0).getParentId());
		Assert.assertEquals(parents.getStatus(),"success");
		Assert.assertEquals(parents.getCode().longValue(),100L);
	}
	
	@Test
	public void testNullGetTasks() {
		Mockito.when(taskRepo.findAll()).thenReturn(null);
		Tasks tasks = taskService.getTasks();
		Assert.assertEquals(tasks.getTask(),null);
		Assert.assertEquals(tasks.getStatus(),"error");
		Assert.assertEquals(tasks.getCode().longValue(),102);
	}
	
	@Test
	public void testGetTaskById() throws ParseException {
		Mockito.when(taskRepo.findByTaskId(1)).thenReturn(task);
		Assert.assertEquals(taskService.getTaskById(1).getTask().get(0).getTaskId(), task.getTaskId());
	}
	
	@Test
	public void testNullGetTaskById() throws ParseException {
		Mockito.when(taskRepo.findByTaskId(1)).thenReturn(null);
		Assert.assertEquals(taskService.getTaskById(1).getTask(), null);
	}
	
	@Test
	public void testAddTask() {
		Mockito.when(taskRepo.save(task)).thenReturn(task);
		Assert.assertEquals(taskService.addTask(task).getTask().get(0).getTaskName(), task.getTaskName());
	}
	
	@Test
	public void testNullAddTask() {
		Mockito.when(taskRepo.save(task)).thenReturn(null);
		Assert.assertEquals(taskService.addTask(task).getTask(), null);
	}
	
	@Test
	public void testUpdateTask() throws ParseException {
		Task updatedTask = new Task(1,"SIRO", "2019-01-01","2019-12-31",30, "IN PROGRESS", 1,2);
		Mockito.when(taskRepo.save(task)).thenReturn(updatedTask);
		Assert.assertNotEquals(taskService.updateTask(task).getTask().get(0).getPriority(), task.getPriority());
	}
	
	@Test
	public void testNullUpdateTask() throws ParseException {
		Mockito.when(taskRepo.save(task)).thenReturn(null);
		Assert.assertNotEquals(taskService.updateTask(task), null);
	}
	
	@Test
	public void testDeleteTask() throws Exception {
		Mockito.doNothing().when(taskRepo).delete(task);
		Assert.assertNotEquals(taskService.deleteTask(1).getTask(), task);
	}
	
	@Test
	public void testNullDeleteTask() throws Exception {
		Mockito.doNothing().when(taskRepo).delete(null);
		Assert.assertNotEquals(taskService.deleteTask(1), null);
	}
	
	@Test
	public void testTaskById() throws Exception {
		Mockito.when(taskRepo.orderByTaskId()).thenReturn(taskList);
		Assert.assertEquals(taskService.getTasksById().getTask(), taskList);
	}
	
	@Test
	public void testNullTaskById() throws Exception {
		Mockito.when(taskRepo.orderByTaskId()).thenReturn(null);
		Assert.assertEquals(taskService.getTasksById().getTask(), null);
	}

	@Test
	public void testTaskByProjectId() throws Exception {
		Mockito.when(taskRepo.getTaskByProjectId(1)).thenReturn(objList);
		Assert.assertEquals(taskService.getTaskByProjectId(1).getStatus(), "success");
	}
	
	@Test
	public void testTaskByFirstName() throws Exception {
		Mockito.when(taskRepo.orderByStartDate()).thenReturn(objList);
		Assert.assertEquals(taskService.getTasksByStartDate().getStatus(), "success");
	}
	
	@Test
	public void testNullTaskByFirstName() throws Exception {
		Mockito.when(taskRepo.orderByEndDate()).thenReturn(null);
		Assert.assertEquals(taskService.getTasksByEndDate().getTask(), null);
	}
	
	@Test
	public void testTaskByLastName() throws Exception {
		Mockito.when(taskRepo.orderByPriority()).thenReturn(objList);
		Assert.assertEquals(taskService.getTasksByPriority().getStatus(), "success");
	}
	
	@Test
	public void testNullTaskByLastName() throws Exception {
		Mockito.when(taskRepo.orderByStatus()).thenReturn(null);
		Assert.assertEquals(taskService.getTasksByStatus().getTask(), null);
	}
	

}
