package com.nutrimedica.nutrimedica_api.controller;

import com.nutrimedica.nutrimedica_api.dto.Receptionist;
import com.nutrimedica.nutrimedica_api.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/receptionists")
public class ReceptionistController {

	private final ReceptionistService receptionistService;

	@Autowired
	public ReceptionistController(ReceptionistService receptionistService) {
		this.receptionistService = receptionistService;
	}

	@PostMapping
	public String createReceptionist(@RequestBody Receptionist receptionist) {
		receptionistService.createReceptionist(receptionist);
		return "Receptionist created successfully!";
	}

	@PutMapping
	public String updateReceptionist(@RequestBody Receptionist receptionist) {
		receptionistService.updateReceptionist(receptionist);
		return "Receptionist updated successfully!";
	}
}
