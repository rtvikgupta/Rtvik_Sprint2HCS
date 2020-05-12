package com.capgemini.rtvik.healthcare;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.rtvik.healthcare.entities.User;
import com.capgemini.rtvik.healthcare.exception.UserAlreadyExistsException;
import com.capgemini.rtvik.healthcare.exception.UserNotFoundException;
import com.capgemini.rtvik.healthcare.service.IUserService;
import com.capgemini.rtvik.healthcare.service.UserServiceImpl;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(UserServiceImpl.class)
class UserManagementApplicationTests {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private EntityManager em;
	
	/**
	 * case when user doesn't exist in database before
	 */
//	@Test
//	public void testAddUser_1() {
//		String userPassword = "rtvik", userName = "rtvik" , userEmail = "rtvik@gmail.com", gender = "Male";
//		BigInteger contact = new BigInteger("9810654577");
//		int age = 22;
//		
//		User user = new User();
//		user.setUserId(userEmail);
//		user.setUserName(userName);
//		user.setUserPassword(userPassword);
//		user.setUserEmail(userEmail);
//		user.setGender(gender);
//		user.setContactNo(contact);
//		user.setAge(age);
//		User result = service.registerUser(user);
//		List<User> fetched = em.createQuery("FROM User").getResultList();
//		Assertions.assertEquals(1, fetched.size());
//		User expected = fetched.get(0);
//		Assertions.assertEquals(expected, result);
//	}
	
	/**
	 * case when user already exists and again adding same user, verifying UserAlreadyExistsException is thrown
	 * precondition: user exists in database
	 */
	@Test
	public void testAddUser_2() {
		String userPassword = "rtvik", userName = "rtvik" , userEmail = "rtvik@gmail.com", gender = "Male";
		BigInteger contact = new BigInteger("9810654577");
		int age = 22;
		
		User user = new User();
		user.setUserId(userEmail);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setUserEmail(userEmail);
		user.setGender(gender);
		user.setContactNo(contact);
		user.setAge(age);
		User result = em.merge(user);
		Executable executable = () -> service.registerUser(result);
		Assertions.assertThrows(UserAlreadyExistsException.class, executable);
	}
	
	/**
	 * case when user doesn't exist, verifying UserNotFoundException is thrown
	 */
	@Test
	public void testByUserId_1() {
		Executable executable = () -> service.findById("rtvik@gmail.com");
		Assertions.assertThrows(UserNotFoundException.class	, executable);
	}
	
	/**
	 * case when user exists, verifying user details are correctly fetched
	 * precondition: user exists in database
	 */
	@Test
	public void testByUserId_2() {
		String userPassword = "rtvik", userName = "rtvik" , userEmail = "rtvik@gmail.com", gender = "Male";
		BigInteger contact = new BigInteger("9810654577");
		int age = 22;
		User user = new User();
		user.setUserId(userEmail);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setUserEmail(userEmail);
		user.setGender(gender);
		user.setContactNo(contact);
		user.setAge(age);
		
		user = em.merge(user);
		User result = service.findById(user.getUserId());
		
		Assertions.assertEquals(user, result);
		Assertions.assertEquals(userPassword, user.getUserPassword());
		Assertions.assertEquals(userName, user.getUserName());
		Assertions.assertEquals(userEmail, user.getUserEmail());
		Assertions.assertEquals(gender, user.getGender());
		Assertions.assertEquals(contact, user.getContactNo());
		Assertions.assertEquals(age, user.getAge());
	}
}
