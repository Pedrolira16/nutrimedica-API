package com.nutrimedica.nutrimedica_api.repository;

import com.nutrimedica.nutrimedica_api.dto.Attendance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AttendanceRepository {

	private final JdbcTemplate jdbcTemplate;

	public AttendanceRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createAttendance(Attendance attendance) {
		String sql = "INSERT INTO attendances (patient_id, user_id, place_id, agenda_event_id, done, start_date, end_date, observation)" +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, attendance.getPatientId(), attendance.getUserId(), attendance.getPlaceId(),
							attendance.getAgendaEventId(), attendance.isDone(), attendance.getStartDate(),
							attendance.getEndDate(), attendance.getObservation());
	}

	public List<Attendance> getAttendances() {
		String sql = "SELECT * FROM attendances";
		return jdbcTemplate.query(
			sql,
			(rs, rowNum) -> new Attendance(
				rs.getLong("id"),
				rs.getLong("patient_id"),
				rs.getLong("user_id"),
				rs.getLong("place_id"),
				rs.getLong("agenda_event_id"),
				rs.getBoolean("done"),
				rs.getDate("start_date"),
				rs.getDate("end_date"),
				rs.getString("observation")
			)
		);
	}

	public void deleteAttendance(Long id) {
		String sql = "DELETE FROM attendances WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void updateAttendance(Attendance attendance) {
		String sql = "UPDATE attendances SET patient_id = ?, user_id = ?, place_id = ?, agenda_event_id = ?, done = ?, start_date = ?, end_date = ?, observation = ? WHERE id = ?";
		jdbcTemplate.update(sql, attendance.getPatientId(), attendance.getUserId(), attendance.getPlaceId(),
							attendance.getAgendaEventId(), attendance.isDone(), attendance.getStartDate(),
							attendance.getEndDate(), attendance.getObservation(), attendance.getId());
	}

	public List<Attendance> listById(Long id) {
		String sql = "SELECT * FROM attendances WHERE user_id = ?";
		return jdbcTemplate.query(
			sql,
			new Object[]{id},
			(rs, rowNum) -> new Attendance(
				rs.getLong("id"),
				rs.getLong("patient_id"),
				rs.getLong("user_id"),
				rs.getLong("place_id"),
				rs.getLong("agenda_event_id"),
				rs.getBoolean("done"),
				rs.getDate("start_date"),
				rs.getDate("end_date"),
				rs.getString("observation")
			)
		);
	}
}
