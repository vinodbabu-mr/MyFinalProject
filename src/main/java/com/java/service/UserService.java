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

import com.java.dao.UserRepository;
import com.java.model.User;
import com.java.model.Users;

@Service
@RestController
public class UserService {

	@Autowired
	private UserRepository<User> UserRepo;
	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Users getUsers() {

		List<User> userList = new ArrayList<User>();
		Users response = new Users();
		userList = UserRepo.findAll();
		if (userList != null) {
			response.setUser(userList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/usersById", method = RequestMethod.GET)
	public Users getUsersById() {

		List<User> userList = new ArrayList<User>();
		Users response = new Users();
		userList = UserRepo.orderByUserId();
		if (userList != null) {
			response.setUser(userList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/usersByFirstName", method = RequestMethod.GET)
	public Users getUsersByFirstName() {

		List<User> userList = new ArrayList<User>();
		Users response = new Users();
		userList = UserRepo.orderByFirstName();
		if (userList != null) {
			response.setUser(userList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/usersByLastName", method = RequestMethod.GET)
	public Users getUsersByLastName() {

		List<User> userList = new ArrayList<User>();
		Users response = new Users();
		userList = UserRepo.orderByLastName();
		if (userList != null) {
			response.setUser(userList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public Users getUserById(@PathVariable("id") Integer userId) {
		Users response = new Users();
		List<User> userList = new ArrayList<User>();
		User user = UserRepo.findByUserId(userId);
		if (user != null) {
			userList.add(user);
			response.setUser(userList);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Users addUser(@RequestBody User user) {
		Users response = new Users();
		user = UserRepo.save(user);
		List<User> userLst = new ArrayList<>();
		if (user != null) {
			userLst.add(user);
			response.setUser(userLst);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/modifyUser", method = RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Users updateUser(@RequestBody User user) {
		Users response = new Users();
		user = UserRepo.save(user);
		List<User> userLst = new ArrayList<>();
		if (user != null) {
			userLst.add(user);
			response.setUser(userLst);
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}

	//
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Users deleteUser(@PathVariable("id") Integer UserId) {
		Users response = new Users();
		try {
			User user = UserRepo.findByUserId(UserId);
			UserRepo.delete(user);
			response.setStatus("User Deleted Successfully");
			response.setCode(CODE_SUCCESS);
		} catch (Exception ex) {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
			ex.printStackTrace();
		}
		return response;
	}
}
