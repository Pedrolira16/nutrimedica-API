package com.nutrimedica.nutrimedica_api.controller;

import com.nutrimedica.nutrimedica_api.dto.Patient;
import com.nutrimedica.nutrimedica_api.service.PatientService;
import com.nutrimedica.nutrimedica_api.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

	@PostMapping
	public String createPatient(@RequestBody Patient patient, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Missing or invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		patientService.createPatient(patient);
		return "Patient created successfully!";
	}

	@GetMapping
	public ResponseEntity<?> getPatients(HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Missing or invalid token.");
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		List<Patient> patients = patientService.getPatients();
		return ResponseEntity.ok(patients);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable Long id, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Missing or invalid token.");
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		patientService.deletePatient(id);
		return ResponseEntity.ok("Patient deleted successfully!");
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody Patient patient, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Missing or invalid token.");
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		patientService.updatePatient(id, patient);
		return ResponseEntity.ok("Patient updated successfully!");
	}
}
