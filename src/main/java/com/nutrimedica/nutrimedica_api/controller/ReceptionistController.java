package com.nutrimedica.nutrimedica_api.controller;

import com.nutrimedica.nutrimedica_api.dto.Receptionist;
import com.nutrimedica.nutrimedica_api.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import com.nutrimedica.nutrimedica_api.utils.JwtUtil;

@RestController
@RequestMapping("users/register/receptionists")
public class ReceptionistController {

	private final ReceptionistService receptionistService;

	@Autowired
	public ReceptionistController(ReceptionistService receptionistService) {
		this.receptionistService = receptionistService;
	}

	@PostMapping
	public String createReceptionist(@RequestBody Receptionist receptionist, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		receptionistService.createReceptionist(receptionist, userId);
		return "Receptionist created successfully!";
	}

	@PutMapping
	public String updateReceptionist(@RequestBody Receptionist receptionist) {
		receptionistService.updateReceptionist(receptionist);
		return "Receptionist updated successfully!";
	}
}
