package com.nutrimedica.nutrimedica_api.controller;

import com.nutrimedica.nutrimedica_api.dto.Attendance;
import com.nutrimedica.nutrimedica_api.service.AttendanceService;
import com.nutrimedica.nutrimedica_api.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

	private final AttendanceService attendanceService;

	@Autowired
	public AttendanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	@PostMapping
	public String createAttendance(@RequestBody Attendance attendance, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		attendanceService.createAttendance(attendance);
		return "Attendance created successfully!";
	}

	@GetMapping
	public ResponseEntity<?> getAttendances(HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		List<Attendance> attendances = attendanceService.getAttendances();
		return ResponseEntity.ok(attendances);
	}

	@GetMapping("/user")
	public ResponseEntity<?> listById(HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		List<Attendance> attendances = attendanceService.listById(userId);

		if (attendances == null || attendances.isEmpty()) {
			return ResponseEntity.ok("Você não tem nenhum atendimento");
		}
		return ResponseEntity.ok(attendances);
	}

	@DeleteMapping("/{id}")
	public String deleteAttendance(@PathVariable Long id, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		attendanceService.deleteAttendance(id);
		return "Attendance deleted successfully!";
	}

	@PutMapping("/{id}")
	public String updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		attendanceService.updateAttendance(id, attendance);
		return "Attendance updated successfully!";
	}
}
