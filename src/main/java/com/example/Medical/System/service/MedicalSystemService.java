package com.example.Medical.System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.MediaList;

import com.example.Medical.System.dto.MedicalSystem;
import com.example.Medical.System.repository.MedicalSystemRepository;

@Service
public class MedicalSystemService {
	
	@Autowired
	MedicalSystemRepository repository;
	
//	CREATE AN APPOINTMENT
	public String createAppointment(MedicalSystem medicalsystem) {
		repository.save(medicalsystem);
		return "Appointment created successfully";
	}
	
//	RETRIEVE APPOINTMENTS BY APPOINTMENT ID
	public MedicalSystem getAppointmentID(int id) {
		Optional<MedicalSystem> opt = repository.findById(id);
		
		if (opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new RuntimeException("Enter Valid ID");
		}
	}
	
//	UPDATE DOCTOR NAME AND APPOINTMENT STATUS BASED ON ID
	public String updateDoctorAndStatus(int id, String doctor_name, String status) {
		MedicalSystem ms = getAppointmentID(id);
		
		if (ms != null) {
			ms.setDoctor_name(doctor_name);
			ms.setStatus(status);
			repository.save(ms);
			return "Doctor name and Status updated Successfully";
		}
		else {
			return "Enter Valid ID";
		}
	}
	
//	DELETE APPOINTMENT BASED ON ID
	public String deleteAppointment(int id) {
		MedicalSystem ms = getAppointmentID(id);
		if ( ms != null) {
			repository.delete(ms);
			return "Appointment ID"+ms.getId()+" deleted";
		}else {
			return "Enter Valid ID";
		}
	}
	
//	DELETE APPOINTMENT WHOSE STATUS IS CANCELLED -error
	public String deleteApopintmentBasedOnStatus(String status) {
		int delete = repository.deleteAppointmentBasedOnStatus(status);
		if ( delete > 0) {
			return "DELETED :"+delete+" Appointments";
		}
		else {
			return "No appointments found with status"+status;
		}
	}
	
//	RETRIEVE APPOINTMENTS BY DOCTOR NAME
	public List<MedicalSystem> AppointmentsBasedOnDoctor(String doctor_name){
		return repository.getAppointmentsofDoctor(doctor_name);
	}
	
//	FIND APPOINTMENTS ON SPECIFIC DATE
	public List<MedicalSystem> AppointmentsBasedOnDate(String date){
		return repository.getAppointmentsOnDate(date);
	}
	
//	CHECK AVAILABLE TIME SLOTS FOR A DOCTOR
	
//	DISPLAY ALL APPOITMENTS WHICH ARE CONFIRMED
	public List<MedicalSystem> AppointmentsBasedOnConfirmation(String status){
		return repository.getAppointmentsConfirmed(status);
	}
	
	
//	UPDATE PATIENT NAME AND APPOINTMENT DATE BASED ON ID
	public String updatePatientNameAndDate(int id , String patient_name, String date) {
		MedicalSystem ms = getAppointmentID(id);
		if (ms != null) {
			ms.setPatient_name(patient_name);
			ms.setDate(date);
			repository.save(ms);
			return "Updated successfully";
		}
		else {
			return "Enter Valid ID";
		}
	}

}
