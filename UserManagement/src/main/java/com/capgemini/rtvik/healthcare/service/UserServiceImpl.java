package com.capgemini.rtvik.healthcare.service;

import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.rtvik.healthcare.dao.IUserDao;
import com.capgemini.rtvik.healthcare.entities.User;
import com.capgemini.rtvik.healthcare.exception.UserAlreadyExistsException;
import com.capgemini.rtvik.healthcare.exception.UserNotFoundException;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao dao;
	
	@Override
	public String registerUser(User user) {
		if(dao.findByUserEmail(user.getUserEmail())==null)
		{
			String id = generateId();
			user.setUserId(id);
			user = dao.save(user);
<<<<<<< HEAD
			return user.getUserId().toString();
=======
			return user.getUserId();
>>>>>>> f2bdae8c1711e9db74966d37e9e2a4f490c636e9
		}
		throw new UserAlreadyExistsException("User Already exists for email-id: "+user.getUserEmail());
	}
	

	@Override
	public User findById(String id) {
		Optional<User> optional = dao.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			return user;
		}
		throw new UserNotFoundException("No User found for id: "+id);
	}
	
	public String generateId() {
		StringBuilder id = new StringBuilder();
		for(int i=0;i<10;i++) {
			Random random = new Random();
			int digit = random.nextInt(9);
			id.append(digit);
		}
		return id.toString();
	}

}
