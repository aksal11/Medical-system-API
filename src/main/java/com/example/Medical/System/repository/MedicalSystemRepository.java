package com.example.Medical.System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Medical.System.dto.MedicalSystem;

import jakarta.transaction.Transactional;


@Repository
public interface MedicalSystemRepository extends JpaRepository<MedicalSystem, Integer> {
	
	
//	APPOINTMENTS BY DOCTOR NAME
	@Query("select m from MedicalSystem m where m.doctor_name=?1")
	public List<MedicalSystem> getAppointmentsofDoctor(String name);
	
	
//	APPOINTMENT WHOSE STATUS IS CANCELLED
	@Modifying
	@Transactional
	@Query("delete from MedicalSystem m where m.status=?1")
	public int deleteAppointmentBasedOnStatus(String status);
	
	
//	APPOINTMENTS ON SPECIFIC DATE
	@Query("select m from MedicalSystem m where m.date=?1")
	public List<MedicalSystem> getAppointmentsOnDate(String date);
	
//	APPOINTMENTS WHICH ARE CONFIRMED
	@Query("select m from MedicalSystem m where m.status=?1")
	public List<MedicalSystem> getAppointmentsConfirmed(String status);
	
	

}
