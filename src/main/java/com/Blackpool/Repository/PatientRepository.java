package com.Blackpool.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blackpool.Models.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
	
	public List<Patient> findByOrderByAppointmentAsc();
}
