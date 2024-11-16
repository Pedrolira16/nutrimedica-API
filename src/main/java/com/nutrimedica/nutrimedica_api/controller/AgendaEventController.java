package com.nutrimedica.nutrimedica_api.controller;

import com.nutrimedica.nutrimedica_api.dto.AgendaEvent;
import com.nutrimedica.nutrimedica_api.service.AgendaEventService;
import com.nutrimedica.nutrimedica_api.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/agenda-events")
public class AgendaEventController {

	private final AgendaEventService agendaEventService;

	@Autowired
	public AgendaEventController(AgendaEventService agendaEventService) {
		this.agendaEventService = agendaEventService;
	}

	@PostMapping
	public String createAgendaEvent(@RequestBody AgendaEvent agendaEvent, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		agendaEventService.createAgendaEvent(agendaEvent);
		return "Agenda event created successfully!";
	}

	@GetMapping
	public ResponseEntity<?> getAgendaEvents(HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		List<AgendaEvent> agendaEvents = agendaEventService.getAgendaEvents();
		return ResponseEntity.ok(agendaEvents);
	}

	@DeleteMapping("/{id}")
	public String deleteAgendaEvent(@PathVariable Long id, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		agendaEventService.deleteAgendaEvent(id);
		return "Agenda event deleted successfully!";
	}

	@PutMapping("/{id}")
	public String updateAgendaEvent(@PathVariable Long id, @RequestBody AgendaEvent agendaEvent, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		agendaEventService.updateAgendaEvent(id, agendaEvent);
		return "Agenda event updated successfully!";
	}
}
