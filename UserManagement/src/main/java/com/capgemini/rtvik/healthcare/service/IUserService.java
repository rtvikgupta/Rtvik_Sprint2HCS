package com.capgemini.rtvik.healthcare.service;

import com.capgemini.rtvik.healthcare.entities.User;

public interface IUserService {
	
	String registerUser(User user);
	
	User findById(String id);
}
