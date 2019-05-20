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

import com.java.dao.ProjectRepository;
import com.java.model.Project;
import com.java.model.Projects;

@Service
@RestController
public class ProjectService {

	@Autowired
	private ProjectRepository<Project> ProjectRepo;
	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public Projects getProjects() {

		List<Project> projectList = new ArrayList<Project>();
		Projects response = new Projects();
		projectList = ProjectRepo.findAll();
		if (projectList != null) {
			response.setProject(projectList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/projectsById", method = RequestMethod.GET)
	public Projects getProjectsById() {

		List<Project> projectList = new ArrayList<Project>();
		Projects response = new Projects();
		projectList = ProjectRepo.orderByProjectId();
		if (projectList != null) {
			response.setProject(projectList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/projectsByStartDate", method = RequestMethod.GET)
	public Projects getProjectsByStartDate() {

		List<Project> projectList = new ArrayList<Project>();
		Projects response = new Projects();
		projectList = ProjectRepo.orderByStartDate();
		if (projectList != null) {
			response.setProject(projectList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/projectsByEndDate", method = RequestMethod.GET)
	public Projects getProjectsByEndDate() {

		List<Project> projectList = new ArrayList<Project>();
		Projects response = new Projects();
		projectList = ProjectRepo.orderByEndDate();
		if (projectList != null) {
			response.setProject(projectList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}
	
	@RequestMapping(value = "/projectsByPriority", method = RequestMethod.GET)
	public Projects getProjectsByPriority() {

		List<Project> projectList = new ArrayList<Project>();
		Projects response = new Projects();
		projectList = ProjectRepo.orderByPriority();
		if (projectList != null) {
			response.setProject(projectList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}
	
	@RequestMapping(value = "/projectsByStatus", method = RequestMethod.GET)
	public Projects getProjectsByStatus() {

		List<Project> projectList = new ArrayList<Project>();
		Projects response = new Projects();
		projectList = ProjectRepo.orderByStatus();
		if (projectList != null) {
			response.setProject(projectList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public Projects getProjectById(@PathVariable("id") Integer projectId) {
		Projects response = new Projects();
		List<Project> projectList = new ArrayList<Project>();
		Project project = ProjectRepo.findByProjectId(projectId);
		if (project != null) {
			projectList.add(project);
			response.setProject(projectList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Projects addProject(@RequestBody Project project) {
		Projects response = new Projects();
		project = ProjectRepo.save(project);
		List<Project> projectLst = new ArrayList<>();
		if (project != null) {
			projectLst.add(project);
			response.setProject(projectLst);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/modifyProject", method = RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Projects updateProject(@RequestBody Project project) {
		Projects response = new Projects();
		project = ProjectRepo.save(project);
		List<Project> projectLst = new ArrayList<>();
		if (project != null) {
			projectLst.add(project);
			response.setProject(projectLst);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	//
	@RequestMapping(value = "/deleteProject/{id}", method = RequestMethod.DELETE)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Projects deleteProject(@PathVariable("id") Integer ProjectId) {
		Projects response = new Projects();
		try {
			Project project = ProjectRepo.findByProjectId(ProjectId);
			ProjectRepo.delete(project);
			response.setStatus("Project Deleted Successfully");
			response.setCode(CODE_SUCCESS);
		} catch (Exception ex) {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
			ex.printStackTrace();
		}
		return response;
	}
}
