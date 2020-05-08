package com.capgemini.rtvik.healthcare.service;

import java.math.BigInteger;
import java.util.List;

import com.capgemini.rtvik.healthcare.entities.Appointment;

public interface IAppointmentService {
	
	public Appointment approveAppointment(Appointment app);
	public Appointment saveAppointment(Appointment app);
	public Appointment findById(BigInteger appointmentId);
	public List<Appointment> findByCenter(String centerId);
	public List<Appointment> findByUser(String userId);
}
