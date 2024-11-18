package com.nutrimedica.nutrimedica_api.service;

import com.nutrimedica.nutrimedica_api.dto.Receptionist;
import com.nutrimedica.nutrimedica_api.repository.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceptionistService {

	private final ReceptionistRepository receptionistRepository;

	@Autowired
	public ReceptionistService(ReceptionistRepository receptionistRepository) {
		this.receptionistRepository = receptionistRepository;
	}

	public void createReceptionist(Receptionist receptionist) {
		receptionistRepository.createReceptionist(receptionist);
	}

	public void updateReceptionist(Receptionist receptionist) {
		receptionistRepository.updateReceptionist(receptionist);
	}
}
