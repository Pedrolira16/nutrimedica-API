package com.nutrimedica.nutrimedica_api.service;

import com.nutrimedica.nutrimedica_api.dto.Patient; // Importe do pacote correto
import com.nutrimedica.nutrimedica_api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {

	private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

	public void createPatient(Patient patient) {
		patientRepository.createPatient(patient);
	}

	public List<Patient> getPatients() {
		return patientRepository.getPatients();
	}

	public void deletePatient(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Paciente não encontrado.");
		}

		patientRepository.deletePatient(id);
	}

	public void updatePatient(Long id, Patient patient) {
		patient.setId(id);
		patientRepository.updatePatient(patient);
	}
}
