package com.capgemini.rtvik.healthcare;

import java.math.BigInteger;
import java.time.LocalDateTime;
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

import com.capgemini.rtvik.healthcare.entities.Appointment;
import com.capgemini.rtvik.healthcare.exceptions.AppointmentNotFound;
import com.capgemini.rtvik.healthcare.exceptions.CenterNotFound;
import com.capgemini.rtvik.healthcare.exceptions.UserNotFound;
import com.capgemini.rtvik.healthcare.service.AppointmentServiceImpl;
import com.capgemini.rtvik.healthcare.service.IAppointmentService;
import com.capgemini.rtvik.healthcare.util.Util;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(AppointmentServiceImpl.class)
class AppointmentManagementApplicationTests {

	@Autowired
	private IAppointmentService service;
	
	@Autowired
	private EntityManager em;
	
	/**
	 * case when appointment does not exist in database before
	 */
	@Test
	public void testSaveAppointment() {
		LocalDateTime dateTime = Util.convertStringToDate("10-05-2020 11:00");
		boolean status = false;
		String userId = "1011", centerId = "1222", testId = "201";
		Appointment appointment = new Appointment();
		appointment.setCenterId(centerId);
		appointment.setDateTime(dateTime);
		appointment.setStatus(status);
		appointment.setTestId(testId);
		appointment.setUserId(userId);
		String result = service.makeAppointment(userId, centerId, testId, dateTime);
		List<Appointment> fetched = em.createQuery("FROM Appointment").getResultList();
		Assertions.assertEquals(1, fetched.size());
		Appointment app = fetched.get(0);
		String expected = app.getAppointmentId().toString();
		Assertions.assertEquals(expected, result);
	}
	
	/**
	 * case when appointment exists, verifying appointment status is true
	 * precondition: appointment exists in database
	 */
	@Test
	public void testApproveAppointment() {
		LocalDateTime dateTime = Util.convertStringToDate("10-05-2020 11:00");
		boolean status = false;
		String userId = "1011", centerId = "1222", testId = "201";
		Appointment appointment = new Appointment();
		appointment.setCenterId(centerId);
		appointment.setDateTime(dateTime);
		appointment.setStatus(status);
		appointment.setTestId(testId);
		appointment.setUserId(userId);
		boolean approve = service.approveAppointment(appointment);
		Assertions.assertEquals(true, approve);
	}
	
	/**
	 * case when appointment exists , verifying appointment is correctly fetched
	 * precondition : appointment exists in database
	 */
	@Test
	public void testFindByAppointmentId_1() {
		LocalDateTime dateTime = Util.convertStringToDate("10-05-2020 11:00");
		boolean status = false;
		String userId = "1011", centerId = "1222", testId = "201";
		Appointment appointment = new Appointment();
		appointment.setCenterId(centerId);
		appointment.setDateTime(dateTime);
		appointment.setStatus(status);
		appointment.setTestId(testId);
		appointment.setUserId(userId);
		
		appointment = em.merge(appointment);
		BigInteger apId = appointment.getAppointmentId();
		Appointment result = service.findById(apId);
		
		Assertions.assertEquals(appointment, result);
		Assertions.assertEquals(dateTime, appointment.getDateTime());
		Assertions.assertEquals(status, appointment.isStatus());
		Assertions.assertEquals(userId, appointment.getUserId());
		Assertions.assertEquals(centerId, appointment.getCenterId());
		Assertions.assertEquals(testId, appointment.getTestId());
		
	}
	
	/**
	 * case when appointment doesn't exist, verifying AppointmentNotFound exception is thrown
	 */
	@Test
	public void testFindByAppointmentId_2(){
		BigInteger id = new BigInteger("1");
		Executable executable = () -> service.findById(id); 
		Assertions.assertThrows(AppointmentNotFound.class, executable);
	}
	
	/**
	 * case when appointment in center doesn't exist, verifying CenterNotFound exception is thrown
	 */
	@Test
	public void testFindByCenterId() {
		Executable executable = () -> service.findByCenter("1220"); 
		Assertions.assertThrows(CenterNotFound.class, executable);
	}
	
	/**
	 * case when appointment in user doesn't exist, verifying UserNotFound exception is thrown
	 */
	@Test
	public void testFindByUserId() {
		Executable executable = () -> service.findByUser("201");
		Assertions.assertThrows(UserNotFound.class, executable);
	}
	
}
