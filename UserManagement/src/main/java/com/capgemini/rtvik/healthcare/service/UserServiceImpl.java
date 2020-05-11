package com.capgemini.rtvik.healthcare.service;

import java.util.Optional;

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
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		if(!dao.findById(user.getUserEmail()).isPresent())
		{
			user = dao.save(user);
			return user;
		}
		throw new UserAlreadyExistsException("User Already exists for id: "+user.getUserEmail());
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		Optional<User> optional = dao.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			return user;
		}
		throw new UserNotFoundException("No User found for id: "+id);
	}

}
