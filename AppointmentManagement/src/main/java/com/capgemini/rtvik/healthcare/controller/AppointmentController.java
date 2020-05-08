package com.capgemini.rtvik.healthcare.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.rtvik.healthcare.dto.AppointmentDetailsDto;
import com.capgemini.rtvik.healthcare.dto.AppointmentDto;
import com.capgemini.rtvik.healthcare.entities.Appointment;
import com.capgemini.rtvik.healthcare.service.IAppointmentService;
import com.capgemini.rtvik.healthcare.util.Util;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private IAppointmentService service;
	
	/**
	 * Make Appointment
	 * @param dto
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<AppointmentDetailsDto> addAppointment(@RequestBody AppointmentDto dto) {
		Appointment appointment = convertDto(dto);
		appointment = service.saveAppointment(appointment);
		AppointmentDetailsDto detailsDto = convertAppointmentDetails(appointment); 
		ResponseEntity<AppointmentDetailsDto> response = new ResponseEntity<AppointmentDetailsDto>(detailsDto, HttpStatus.OK);
		return response;
	}
	
	/**
	 * convert from appointment: dto -> entity
	 * @param dto
	 * @return
	 */
	public Appointment convertDto(AppointmentDto dto) {
		Appointment app = new Appointment();
		app.setCenterId(dto.getCenterId());
		app.setUserId(dto.getUserId());
		app.setTestId(dto.getTestId());
		LocalDateTime dateTime = Util.convertStringToDate(dto.getDateTime());
		app.setDateTime(dateTime);
		//app.setStatus(false);
		return app;
	}
	
	/**
	 * convert from appointment: entity -> detailsdto
	 * @param app
	 * @return
	 */
	public AppointmentDetailsDto convertAppointmentDetails(Appointment app) {
		AppointmentDetailsDto detailsDto = new AppointmentDetailsDto();
		detailsDto.setAppointmentId(app.getAppointmentId());
		detailsDto.setCenterId(app.getCenterId());
		detailsDto.setDateTime(app.getDateTime().toString());
		detailsDto.setStatus(app.isStatus());
		detailsDto.setTestId(app.getTestId());
		detailsDto.setUserId(app.getUserId());
		return detailsDto;
	}
	
	/**
	 * convert form appintments: entity list -> detailsdto list
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
	public ResponseEntity<AppointmentDetailsDto> approveAppointment(@PathVariable("id") BigInteger appointmentId) {
		Appointment appoint = service.findById(appointmentId);
		Appointment app = service.approveAppointment(appoint);
		AppointmentDetailsDto detailsDto = convertAppointmentDetails(app); 
		ResponseEntity<AppointmentDetailsDto> response = new ResponseEntity<AppointmentDetailsDto>(detailsDto, HttpStatus.OK);
		return response;
	}
}
