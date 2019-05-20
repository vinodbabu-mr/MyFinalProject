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

import com.java.dao.ProjectRepository;
import com.java.model.Project;
import com.java.model.Projects;
import com.java.rest.RestfulApplication;

import org.junit.Assert;
import org.junit.Before;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RestfulApplication.class)
@SpringBootTest
@ActiveProfiles("CloudX")
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;

	@MockBean
	private ProjectRepository<Project> projectRepo;
	
	Project project;
	List<Project> projectList  = new ArrayList<Project>();
	Projects response;
	
	@Before
	public void setUp() throws ParseException {
		project = new Project(1,"CONGO", "2019-01-01","2019-12-31",10, "IN PROGRESS", 1);
		projectList.add(project);
		response = new Projects();
		response.setProject(projectList);
		response.setStatus("success");
		response.setCode(100);
	}

	@Test
	public void testGetProjects() {
		Mockito.when(projectRepo.findAll()).thenReturn(projectList);
		Projects projects = projectService.getProjects();
		List<Project> projectLst = projects.getProject();
		Assert.assertEquals(projectLst.get(0).getProjectName(),projectList.get(0).getProjectName());
		Assert.assertEquals(projectLst.get(0).getStartDate(),projectList.get(0).getStartDate());
		Assert.assertEquals(projectLst.get(0).getEndDate(),projectList.get(0).getEndDate());
		Assert.assertEquals(projectLst.get(0).getPriority(),projectList.get(0).getPriority());
		Assert.assertEquals(projectLst.get(0).getStatus(),projectList.get(0).getStatus());
		Assert.assertEquals(projectLst.get(0).getUserId(),projectList.get(0).getUserId());
		Assert.assertEquals(projects.getStatus(),"success");
		Assert.assertEquals(projects.getCode().longValue(),100L);
		Assert.assertNotNull(project.toString());
	}
	
	@Test
	public void testNullGetProjects() {
		Mockito.when(projectRepo.findAll()).thenReturn(null);
		Projects projects = projectService.getProjects();
		Assert.assertEquals(projects.getProject(),null);
		Assert.assertEquals(projects.getStatus(),"error");
		Assert.assertEquals(projects.getCode().longValue(),102);
	}
	
	@Test
	public void testGetProjectById() throws ParseException {
		Mockito.when(projectRepo.findByProjectId(1)).thenReturn(project);
		Assert.assertEquals(projectService.getProjectById(1).getProject().get(0).getProjectId(), project.getProjectId());
	}
	
	@Test
	public void testNullGetProjectById() throws ParseException {
		Mockito.when(projectRepo.findByProjectId(1)).thenReturn(null);
		Assert.assertEquals(projectService.getProjectById(1).getProject(), null);
	}
	
	@Test
	public void testAddProject() {
		Mockito.when(projectRepo.save(project)).thenReturn(project);
		Assert.assertEquals(projectService.addProject(project).getProject().get(0).getProjectName(), project.getProjectName());
	}
	
	@Test
	public void testNullAddProject() {
		Mockito.when(projectRepo.save(project)).thenReturn(null);
		Assert.assertEquals(projectService.addProject(project).getProject(), null);
	}
	
	@Test
	public void testUpdateProject() throws ParseException {
		Project updatedProject = new Project(1,"SIRO", "2019-01-01","2019-12-31",20, "IN PROGRESS", 1);
		Mockito.when(projectRepo.save(project)).thenReturn(updatedProject);
		Assert.assertNotEquals(projectService.updateProject(project).getProject().get(0).getPriority(), project.getPriority());
	}
	
	@Test
	public void testNullUpdateProject() throws ParseException {
		Mockito.when(projectRepo.save(project)).thenReturn(null);
		Assert.assertNotEquals(projectService.updateProject(project), null);
	}
	
	@Test
	public void testDeleteProject() throws Exception {
		Mockito.doNothing().when(projectRepo).delete(project);
		Assert.assertNotEquals(projectService.deleteProject(1).getProject(), project);
	}
	
	@Test
	public void testNullDeleteProject() throws Exception {
		Mockito.doNothing().when(projectRepo).delete(null);
		Assert.assertNotEquals(projectService.deleteProject(1), null);
	}
	
	@Test
	public void testProjectById() throws Exception {
		Mockito.when(projectRepo.orderByProjectId()).thenReturn(projectList);
		Assert.assertEquals(projectService.getProjectsById().getProject(), projectList);
	}
	
	@Test
	public void testNullProjectById() throws Exception {
		Mockito.when(projectRepo.orderByProjectId()).thenReturn(null);
		Assert.assertEquals(projectService.getProjectsById().getProject(), null);
	}
	
	@Test
	public void testProjectByFirstName() throws Exception {
		Mockito.when(projectRepo.orderByStartDate()).thenReturn(projectList);
		Assert.assertEquals(projectService.getProjectsByStartDate().getProject(), projectList);
	}
	
	@Test
	public void testNullProjectByFirstName() throws Exception {
		Mockito.when(projectRepo.orderByEndDate()).thenReturn(null);
		Assert.assertEquals(projectService.getProjectsByEndDate().getProject(), null);
	}
	
	@Test
	public void testProjectByLastName() throws Exception {
		Mockito.when(projectRepo.orderByPriority()).thenReturn(projectList);
		Assert.assertEquals(projectService.getProjectsByPriority().getProject(), projectList);
	}
	
	@Test
	public void testNullProjectByLastName() throws Exception {
		Mockito.when(projectRepo.orderByStatus()).thenReturn(null);
		Assert.assertEquals(projectService.getProjectsByStatus().getProject(), null);
	}
	

}
