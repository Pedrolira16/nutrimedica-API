package com.nutrimedica.nutrimedica_api.service;

import com.nutrimedica.nutrimedica_api.dto.Doctor;
import com.nutrimedica.nutrimedica_api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {

	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	public void createDoctor(Doctor doctor, Long userId) {
		doctor.setUserId(userId);
		doctorRepository.createDoctor(doctor);
	}

	public void updateDoctor(Long id, Doctor doctor) {
		doctor.setUserId(id);
		doctorRepository.updateDoctor(doctor);
	}

	public void addSpecialty(Long id, String name) {
		Long specialtyId = doctorRepository.createSpecialty(name);
		doctorRepository.addDoctorSpecialty(id, specialtyId);
	}
}
