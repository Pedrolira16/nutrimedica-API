package com.nutrimedica.nutrimedica_api.repository;

import com.nutrimedica.nutrimedica_api.dto.Receptionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReceptionistRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ReceptionistRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createReceptionist(Receptionist receptionist) {
		String sql = "INSERT INTO receptionists (user_id, shift)" +
				"VALUES (?, ?)";

		jdbcTemplate.update(sql, receptionist.getUserId(), receptionist.getShift());
	}

	public void updateReceptionist(Receptionist receptionist) {
		String sql = "UPDATE receptionists SET shift = ? WHERE user_id = ?";
		jdbcTemplate.update(sql, receptionist.getShift(), receptionist.getUserId());
	}
}
