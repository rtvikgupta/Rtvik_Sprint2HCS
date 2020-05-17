package com.capgemini.rtvik.healthcare.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capgemini.rtvik.healthcare.dto.AppointmentDetailsDto;
import com.capgemini.rtvik.healthcare.dto.CreateAppointmentRequest;
import com.capgemini.rtvik.healthcare.dto.DiagnosticCenter;
import com.capgemini.rtvik.healthcare.dto.Test;
import com.capgemini.rtvik.healthcare.entities.Appointment;
import com.capgemini.rtvik.healthcare.exceptions.AppointmentNotFound;
import com.capgemini.rtvik.healthcare.exceptions.CenterNotFound;
import com.capgemini.rtvik.healthcare.exceptions.UserNotFound;
import com.capgemini.rtvik.healthcare.service.IAppointmentService;
import com.capgemini.rtvik.healthcare.util.Util;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	private static final Logger log = LoggerFactory.getLogger(AppointmentController.class); 
	
	@Autowired
	private IAppointmentService service;
	
	@Value("${centertestservice.baseurl}")
	private String centerTestServiceBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	/**
	 * Make Appointment
	 * @param dto
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<String> addAppointment(@RequestBody CreateAppointmentRequest dto) {
		Appointment appointment = convertDto(dto);
		String msg = service.makeAppointment(appointment.getUserId(),appointment.getCenterId(),appointment.getTestId(),appointment.getDateTime()); 
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		return response;
	}
	
	/**
	 * convert from appointment: dto -> entity
	 * @param dto
	 * @return
	 */
	public Appointment convertDto(CreateAppointmentRequest dto) {
		Appointment app = new Appointment();
		app.setCenterId(dto.getCenterId());
		app.setUserId(dto.getUserId());
		app.setTestId(dto.getTestId());
		LocalDateTime dateTime = Util.convertStringToDate(dto.getDateTime());
		app.setDateTime(dateTime);
		return app;
	}
	
	/**
	 * convert from appointment: entity -> detailsdto
	 * @param app
	 * @return
	 */
	public AppointmentDetailsDto convertAppointmentDetails(Appointment app) {
		AppointmentDetailsDto detailsDto = new AppointmentDetailsDto();
		String id = app.getAppointmentId().toString();
		detailsDto.setAppointmentId(id);
		detailsDto.setCenterId(app.getCenterId());
		detailsDto.setDateTime(app.getDateTime().toString());
		detailsDto.setStatus(app.isStatus());
		detailsDto.setTestId(app.getTestId());
		detailsDto.setUserId(app.getUserId());
		return detailsDto;
	}
	
	/**
	 * convert form appointments: entity list -> detailsdto list
	 * @param appointments
	 * @return
	 */
	public List<AppointmentDetailsDto> convertAppointmentDetails(List<Appointment> appointments) {
		List<AppointmentDetailsDto> listDetailsDto = new ArrayList<AppointmentDetailsDto>();
		for (Appointment appointment : appointments) {
			AppointmentDetailsDto detailsDto = convertAppointmentDetails(appointment);
			listDetailsDto.add(detailsDto);
		}
		return listDetailsDto ;
	}
	
	@GetMapping("/getByCenter/{id}")
	public ResponseEntity<List<Appointment>> viewCenterAppointments(@PathVariable("id") String centerId) {
		List<Appointment> list = service.findByCenter(centerId);
		ResponseEntity<List<Appointment>> response = new ResponseEntity<List<Appointment>>(list,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/getByUser/{id}")
	public ResponseEntity<List<Appointment>> viewUserAppointments(@PathVariable("id") String userId) {
		List<Appointment> list = service.findByUser(userId);
		ResponseEntity<List<Appointment>> response = new ResponseEntity<List<Appointment>>(list,HttpStatus.OK);
		return response;
	}
	
	/**
	 * Approve Appointment
	 * @param appointmentId
	 * @return
	 */
	@PutMapping("/approve/{id}")
	public ResponseEntity<Boolean> approveAppointment(@PathVariable("id") String appointmentId) {
		BigInteger id = new BigInteger(appointmentId);
		Appointment appoint = service.findById(id);
		boolean status = service.approveAppointment(appoint);
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(status, HttpStatus.OK);
		return response;
	}
	
	/**
	 * Fetching all centers
	 * @return
	 */
	@GetMapping("/centers")
	public ResponseEntity<DiagnosticCenter[]> fetchAllCenter() {
		String url = centerTestServiceBaseUrl;
		ResponseEntity<DiagnosticCenter[]> response = restTemplate.getForEntity(url, DiagnosticCenter[].class);
		return response;
	}
	
	/**
	 * Fetching all tests by diagnostic center id
	 * @param centerId
	 * @return
	 */
	@GetMapping("/tests/{id}")
	public ResponseEntity<Test[]> fetchTestsByCenter(@PathVariable("id") String centerId) {
		String url = centerTestServiceBaseUrl + "/show/tests/"+ centerId;
		ResponseEntity<Test[]> response = restTemplate.getForEntity(url, Test[].class);
		return response;
	}
	
	@ExceptionHandler(AppointmentNotFound.class)
	public ResponseEntity<String> handleException1(AppointmentNotFound exception){
		log.error("Appointment Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	     return response;
	}
	
	@ExceptionHandler(CenterNotFound.class)
	public ResponseEntity<String> handleException2(CenterNotFound exception){
		log.error("Appointment Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	     return response;
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<String> handleException3(UserNotFound exception){
		log.error("Appointment Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
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
