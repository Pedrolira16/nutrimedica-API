package com.nutrimedica.nutrimedica_api.controller;

import com.nutrimedica.nutrimedica_api.dto.Place;
import com.nutrimedica.nutrimedica_api.service.PlaceService;
import com.nutrimedica.nutrimedica_api.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/places")
public class PlaceController {

	private final PlaceService placeService;

	@Autowired
	public PlaceController(PlaceService placeService) {
		this.placeService = placeService;
	}

	@PostMapping
	public String createPlace(@RequestBody @Valid Place place, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		placeService.createPlace(place);
		return "Place created successfully!";
}

	@GetMapping
	public ResponseEntity<?> getPlaces(HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token.");
		}

		List<Place> places = placeService.getPlaces();
		return ResponseEntity.ok(places);
	}

	@DeleteMapping("/{id}")
	public String deletePlace(@PathVariable Long id, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		placeService.deletePlace(id);
		return "Place deleted successfully!";
	}

	@PutMapping("/{id}")
	public String updatePlace(@PathVariable Long id, @RequestBody @Valid Place place, HttpServletRequest request) {
		String token = JwtUtil.extractToken(request);

		if (token == null) {
			return "Unauthorized: Invalid token.";
		}

		Long userId = JwtUtil.extractUserId(token);

		if (userId == null) {
			return "Unauthorized: Invalid token.";
		}

		placeService.updatePlace(id, place);
		return "Place updated successfully!";
	}
}
