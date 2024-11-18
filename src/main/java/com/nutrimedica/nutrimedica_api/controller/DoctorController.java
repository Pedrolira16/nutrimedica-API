package com.nutrimedica.nutrimedica_api.controller;

import com.nutrimedica.nutrimedica_api.dto.Doctor;
import com.nutrimedica.nutrimedica_api.service.DoctorService;
import com.nutrimedica.nutrimedica_api.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("users/doctors")
public class DoctorController {

	private final DoctorService doctorService;

	@Autowired
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@PostMapping
	public String createDoctor(@RequestBody Doctor doctor) {
		doctorService.createDoctor(doctor);
		return "Doctor created successfully!";
	}

	@PutMapping("/{id}")
	public String updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		doctorService.updateDoctor(id, doctor);
		return "Doctor updated successfully!";
	}
}
