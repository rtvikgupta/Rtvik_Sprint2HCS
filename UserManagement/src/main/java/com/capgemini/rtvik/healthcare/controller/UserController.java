package com.capgemini.rtvik.healthcare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.rtvik.healthcare.dto.UserDetailsDto;
import com.capgemini.rtvik.healthcare.dto.UserDto;
import com.capgemini.rtvik.healthcare.entities.User;
import com.capgemini.rtvik.healthcare.exception.UserAlreadyExistsException;
import com.capgemini.rtvik.healthcare.exception.UserNotFoundException;
import com.capgemini.rtvik.healthcare.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
	private IUserService service;
	
	@PostMapping("/add")
	public ResponseEntity<UserDetailsDto> registerUser(@RequestBody UserDto dto) {
		User user = convertToDto(dto);
		user = service.registerUser(user);
		UserDetailsDto detailsDto = convertToDetailsDto(user);
		ResponseEntity<UserDetailsDto> response = new ResponseEntity<UserDetailsDto>(detailsDto, HttpStatus.OK);
		return response;
	}
	
	/**
	 * convert from user: dto -> entity
	 * @param dto
	 * @return
	 */
	public User convertToDto(UserDto dto) {
		User user = new User();
		user.setUserId(dto.getUserEmail());
		user.setUserName(dto.getUserName());
		user.setUserPassword(dto.getUserPassword());
		user.setAge(dto.getAge());
		user.setGender(dto.getGender());
		user.setUserEmail(dto.getUserEmail());
		user.setContactNo(dto.getContactNo());
		return user;
	}
	
	/**
	 * convert from user: entity -> details dto
	 * @param user
	 * @return
	 */
	public UserDetailsDto convertToDetailsDto(User user) {
		UserDetailsDto detailsDto = new UserDetailsDto();
		detailsDto.setUserId(user.getUserId());
		detailsDto.setUserName(user.getUserName());
		detailsDto.setUserEmail(user.getUserEmail());
		detailsDto.setAge(user.getAge());
		detailsDto.setGender(user.getGender());
		detailsDto.setContactNo(user.getContactNo());
		return detailsDto;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UserDetailsDto> getUser(@PathVariable("id") String userId) {
		User user = service.findById(userId);
		UserDetailsDto detailsDto = convertToDetailsDto(user);
		ResponseEntity<UserDetailsDto> response = new ResponseEntity<UserDetailsDto>(detailsDto, HttpStatus.OK);
		return response;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleException1(UserNotFoundException exception){
		log.error("User Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	     return response;
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleException2(UserAlreadyExistsException exception){
		log.error("User Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
	     return response;
	}
	
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        log.error("exception caught", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
