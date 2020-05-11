package com.capgemini.rtvik.healthcare.service;

import com.capgemini.rtvik.healthcare.entities.User;

public interface IUserService {
	
	public User registerUser(User user);
	public User findById(String id);
}
