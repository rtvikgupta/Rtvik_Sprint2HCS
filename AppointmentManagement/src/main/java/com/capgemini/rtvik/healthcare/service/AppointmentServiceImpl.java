package com.capgemini.rtvik.healthcare.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.rtvik.healthcare.dao.IAppointmentDao;
import com.capgemini.rtvik.healthcare.entities.Appointment;

@Service
@Transactional
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	private IAppointmentDao dao;

	@Override
	public Appointment saveAppointment(Appointment app) {
		// TODO Auto-generated method stub
		Appointment appoint = dao.save(app);
		return appoint;
	}

	@Override
	public Appointment findById(BigInteger appointmentId) {
		// TODO Auto-generated method stub
		Optional<Appointment> optional = dao.findById(appointmentId);
		if(optional.isPresent())
		{
			Appointment appoint = optional.get();
			return appoint;
		}
		return null;
	}

	@Override
	public List<Appointment> findByCenter(String centerId) {
		// TODO Auto-generated method stub
		List<Appointment> list = dao.findByCenterId(centerId);
		return list;
	}

	@Override
	public List<Appointment> findByUser(String userId) {
		// TODO Auto-generated method stub
		List<Appointment> list = dao.findByUserId(userId);
		return list;
	}

	@Override
	public Appointment approveAppointment(Appointment app) {
		// TODO Auto-generated method stub
		app.setStatus(true);
		Appointment appoint = dao.save(app);
		return appoint;
	}
	
	

}
