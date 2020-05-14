package com.capgemini.rtvik.healthcare.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.rtvik.healthcare.entities.User;

public interface IUserDao extends JpaRepository<User, String> {
	
	User findByUserEmail(String userEmail);
  
}
