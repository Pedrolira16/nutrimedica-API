package com.nutrimedica.nutrimedica_api.repository;

import com.nutrimedica.nutrimedica_api.dto.Doctor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DoctorRepository {

	private final JdbcTemplate jdbcTemplate;

	public DoctorRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createDoctor(Doctor doctor) {
		String sql = "INSERT INTO doctors (user_id, council_name, council_state, council_number)" +
					"VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sql, doctor.getUserId(), doctor.getCouncilName(), doctor.getCouncilState(), doctor.getCouncilNumber());
	}

	public List<Doctor> getDoctors() {
		String sql = "SELECT * FROM doctors";
		return jdbcTemplate.query(
			sql,
			(rs, rowNum) -> new Doctor(
				rs.getLong("user_id"),
				rs.getString("council_name"),
				rs.getString("council_state"),
				rs.getString("council_number")
			)
		);
	}

	public void deleteDoctor(Long id) {
		String sql = "DELETE FROM doctors WHERE user_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void updateDoctor(Doctor doctor) {
		String sql = "UPDATE doctors SET council_name = ?, council_state = ?, council_number = ? WHERE user_id = ?";
		jdbcTemplate.update(sql, doctor.getCouncilName(), doctor.getCouncilState(), doctor.getCouncilNumber(), doctor.getUserId());
	}

	public void addDoctorSpecialty(Long doctorId, Long specialtyId) {
		String sql = "INSERT INTO doctor_specialties (doctor_id, specialty_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, doctorId, specialtyId);
	}

	public Long createSpecialty(String name) {
		String sql = "INSERT INTO specialties (name) VALUES (?)";
		jdbcTemplate.update(sql, name);
		return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
	}
}
