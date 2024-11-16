package com.nutrimedica.nutrimedica_api.repository;

import com.nutrimedica.nutrimedica_api.dto.Place;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PlaceRepository {

	private final JdbcTemplate jdbcTemplate;

	public PlaceRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createPlace(Place place) {
		String sql = "INSERT INTO places (name, address_cep, address, address_number, address_district, address_city, address_state, address_complement)" +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, place.getName(), place.getAddressCep(), place.getAddress(), place.getAddressNumber(),
							place.getAddressDistrict(), place.getAddressCity(), place.getAddressState(), place.getAddressComplement());
	}

	public List<Place> getPlaces() {
		String sql = "SELECT * FROM places";
		return jdbcTemplate.query(
			sql,
			(rs, rowNum) -> new Place(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getString("address_cep"),
				rs.getString("address"),
				rs.getString("address_number"),
				rs.getString("address_district"),
				rs.getString("address_city"),
				rs.getString("address_state"),
				rs.getString("address_complement")
			)
		);
	}

	public void deletePlace(Long id) {
		String sql = "DELETE FROM places WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void updatePlace(Place place) {
		String sql = "UPDATE places SET name = ?, address_cep = ?, address = ?, address_number = ?, address_district = ?, address_city = ?, address_state = ?, address_complement = ? WHERE id = ?";
		jdbcTemplate.update(sql, place.getName(), place.getAddressCep(), place.getAddress(), place.getAddressNumber(),
							place.getAddressDistrict(), place.getAddressCity(), place.getAddressState(), place.getAddressComplement(), place.getId());
	}
}
