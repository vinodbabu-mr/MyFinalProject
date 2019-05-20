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

import com.java.dao.UserRepository;
import com.java.model.User;
import com.java.model.Users;
import com.java.rest.RestfulApplication;

import org.junit.Assert;
import org.junit.Before;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RestfulApplication.class)
@SpringBootTest
@ActiveProfiles("CloudX")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository<User> userRepo;
	
	User user;
	List<User> userList  = new ArrayList<User>();
	Users response;
	
	@Before
	public void setUp() throws ParseException {
		user = new User("Virat", "Kohli",1,null, 400, null);
		user.setEmployeeId(400);
		user.setFirstName("Virat");
		user.setLastName("Kohli");
		user.setUserId(1);
		user.setProjectId(null);
		user.setTaskId(null);
		userList.add(user);
		response = new Users();
		response.setUser(userList);
		response.setStatus("success");
		response.setCode(100);
	}

	@Test
	public void testGetUsers() {
		Mockito.when(userRepo.findAll()).thenReturn(userList);
		Users users = userService.getUsers();
		List<User> userLst = users.getUser();
		Assert.assertEquals(userLst.get(0).getLastName(),userList.get(0).getLastName());
		Assert.assertEquals(userLst.get(0).getProjectId(),userList.get(0).getProjectId());
		Assert.assertEquals(userLst.get(0).getTaskId(),userList.get(0).getTaskId());
		Assert.assertEquals(users.getStatus(),"success");
		Assert.assertEquals(users.getCode().longValue(),100L);
		Assert.assertNotNull(user.toString());
	}
	
	@Test
	public void testNullGetUsers() {
		Mockito.when(userRepo.findAll()).thenReturn(null);
		Users users = userService.getUsers();
		Assert.assertEquals(users.getUser(),null);
		Assert.assertEquals(users.getStatus(),"error");
		Assert.assertEquals(users.getCode().longValue(),102);
	}
	
	@Test
	public void testGetUserById() throws ParseException {
		Mockito.when(userRepo.findByUserId(1)).thenReturn(user);
		Assert.assertEquals(userService.getUserById(1).getUser().get(0).getUserId(), user.getUserId());
	}
	
	@Test
	public void testNullGetUserById() throws ParseException {
		Mockito.when(userRepo.findByUserId(1)).thenReturn(null);
		Assert.assertEquals(userService.getUserById(1).getUser(), null);
	}
	
	@Test
	public void testAddUser() {
		Mockito.when(userRepo.save(user)).thenReturn(user);
		Assert.assertEquals(userService.addUser(user).getUser().get(0).getFirstName(), user.getFirstName());
	}
	
	@Test
	public void testNullAddUser() {
		Mockito.when(userRepo.save(user)).thenReturn(null);
		Assert.assertEquals(userService.addUser(user).getUser(), null);
	}
	
	@Test
	public void testUpdateUser() throws ParseException {
		User updatedUser = new User("Virat", "Kohli",1,null, 401, null);
		Mockito.when(userRepo.save(user)).thenReturn(updatedUser);
		Assert.assertNotEquals(userService.updateUser(user).getUser().get(0).getEmployeeId(), user.getEmployeeId());
	}
	
	@Test
	public void testNullUpdateUser() throws ParseException {
		Mockito.when(userRepo.save(user)).thenReturn(null);
		Assert.assertNotEquals(userService.updateUser(user), null);
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		Mockito.doNothing().when(userRepo).delete(user);
		Assert.assertNotEquals(userService.deleteUser(1).getUser(), user);
	}
	
	@Test
	public void testNullDeleteUser() throws Exception {
		Mockito.doNothing().when(userRepo).delete(null);
		Assert.assertNotEquals(userService.deleteUser(1), null);
	}
	
	@Test
	public void testUserById() throws Exception {
		Mockito.when(userRepo.orderByUserId()).thenReturn(userList);
		Assert.assertEquals(userService.getUsersById().getUser(), userList);
	}
	
	@Test
	public void testNullUserById() throws Exception {
		Mockito.when(userRepo.orderByUserId()).thenReturn(null);
		Assert.assertEquals(userService.getUsersById().getUser(), null);
	}
	
	@Test
	public void testUserByFirstName() throws Exception {
		Mockito.when(userRepo.orderByFirstName()).thenReturn(userList);
		Assert.assertEquals(userService.getUsersByFirstName().getUser(), userList);
	}
	
	@Test
	public void testNullUserByFirstName() throws Exception {
		Mockito.when(userRepo.orderByFirstName()).thenReturn(null);
		Assert.assertEquals(userService.getUsersByFirstName().getUser(), null);
	}
	
	@Test
	public void testUserByLastName() throws Exception {
		Mockito.when(userRepo.orderByLastName()).thenReturn(userList);
		Assert.assertEquals(userService.getUsersByLastName().getUser(), userList);
	}
	
	@Test
	public void testNullUserByLastName() throws Exception {
		Mockito.when(userRepo.orderByLastName()).thenReturn(null);
		Assert.assertEquals(userService.getUsersByLastName().getUser(), null);
	}
	

}
