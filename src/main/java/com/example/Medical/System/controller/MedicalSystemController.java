package com.example.Medical.System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Medical.System.dto.MedicalSystem;
import com.example.Medical.System.service.MedicalSystemService;

@RestController
public class MedicalSystemController {
	
	@Autowired
	MedicalSystemService service;
	
//	REST API TO CREATE AN APPOINTMENT
	@PostMapping("/appointment")
	public String createAppointment(@RequestBody MedicalSystem medicalsystem) {
		return service.createAppointment(medicalsystem);
	}
	
//	REST API TO RETRIEVE APPOINTMENTS BASED ON ID
	@GetMapping("/byid")
	public MedicalSystem getAppointmentBasedOnID(@RequestParam int id){
		return service.getAppointmentID(id);
	}
	
//	REST API TO UPDATE DOCTOR NAME AND APPOINTMENT STATUS BASED ON ID
	@PutMapping("/update")
	public String updateDoctorAndStatus(@RequestParam int id, @RequestParam String doctor_name, @RequestParam String status) {
		return service.updateDoctorAndStatus(id, doctor_name, status);
	}
	
//	REST API TO DELETE APPOINTMENT BASED ON ID
	@DeleteMapping("/appointment")
	public String deleteAppointmentBasedOnId(@RequestParam int id) {
		return service.deleteAppointment(id);
		
	}
	
//	REST API TO DELETE APPOINTMENTS WHOSE STATUS IS CANCELLED
	@DeleteMapping("/deletestatus")
	public String deleteBasedByStatus(@RequestParam String status) {
		if (status == null) {
			return "enter valid status";
		}
		else {
			return service.deleteApopintmentBasedOnStatus(status);
		}
	}
	
//	REST API TO RETRIEVE APPOINTMENTS BY DOCTOR NAME
	@GetMapping("/docbyname")
	public List<MedicalSystem> getAppointmentsByDoctorName(@RequestParam String doctor_name){
		return service.AppointmentsBasedOnDoctor(doctor_name);
	}
	
//	REST API TO FIND APPOINTMENTS ON A SPECIFIC DATE
	@GetMapping("/basedondate")
	public List<MedicalSystem> getAppointmentsBasedOnDate(@RequestParam String date){
		return service.AppointmentsBasedOnDate(date);
	}
	
//	REST API TO CHECK AVAILABLE TIME SLOTS FOR A DOCTOR
	
//	REST API TO  DISPLAY ALL APPOINTMENTS WHICH ARE CONFIRMED
	@GetMapping("/confirmed")
	public List<MedicalSystem> getAppointmentsBasedOnConfirmation(@RequestParam String status){
		return service.AppointmentsBasedOnConfirmation(status);
	}
	
//	REST API TO UPDATE PATIENT NAME AND APPOINTMENT DATE BASED ON ID
	@PutMapping("/updatepatient")
	public String putPatientNameAndDate(@RequestParam int id, @RequestParam String patient_name, String date) {
		return service.updatePatientNameAndDate(id, patient_name, date);
	}

}
