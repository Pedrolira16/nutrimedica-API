package com.nutrimedica.nutrimedica_api.service;

import com.nutrimedica.nutrimedica_api.dto.Place;
import com.nutrimedica.nutrimedica_api.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlaceService {
	private final PlaceRepository placeRepository;

	@Autowired
	public PlaceService(PlaceRepository placeRepository) {
		this.placeRepository = placeRepository;
	}

	public void createPlace(Place place) {
		placeRepository.createPlace(place);
	}

	public List<Place> getPlaces() {
		return placeRepository.getPlaces();
	}

	public void deletePlace(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Local não encontrado.");
		}

		placeRepository.deletePlace(id);
	}

	public void updatePlace(Long id, Place place) {
		place.setId(id);
		placeRepository.updatePlace(place);
	}
}
