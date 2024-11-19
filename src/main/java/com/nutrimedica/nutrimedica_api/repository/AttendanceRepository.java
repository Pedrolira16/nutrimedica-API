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

	public int countCompletedAttendances() {
		String sql = "SELECT COUNT(*) FROM attendances WHERE done = true";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int countTotalAttendances() {
		String sql = "SELECT COUNT(*) FROM attendances";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int countPendingAttendances() {
		String sql = "SELECT COUNT(*) FROM attendances WHERE done = false";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int countcompletdUserAttendances(Long id) {
		String sql = "SELECT COUNT(*) FROM attendances WHERE done = true AND user_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, id);
	}
	public int countTotalUserAttendances(Long id) {
		String sql = "SELECT COUNT(*) FROM attendances WHERE user_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, id);
	}
	public int countPendingUserAttendances(Long id) {
		String sql = "SELECT COUNT(*) FROM attendances WHERE done = false AND user_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, id);
	}

	public int countCompletedAttendancesByUser(Long userId) {
		String sql = "SELECT COUNT(*) FROM attendances WHERE done = true AND user_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, userId);
	}

	public int countTotalAttendancesByUser(Long userId) {
		String sql = "SELECT COUNT(*) FROM attendances WHERE user_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, userId);
	}

	public int countPendingAttendancesByUser(Long userId) {
		String sql = "SELECT COUNT(*) FROM attendances WHERE done = false AND user_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, userId);
	}

	public int countTodayAttendances() {
		String sql = "SELECT COUNT(*) FROM attendances WHERE DATE(start_date) = CURDATE()";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int countWeekAttendances() {
		String sql = "SELECT COUNT(*) FROM attendances WHERE YEARWEEK(DATE(start_date), 1) = YEARWEEK(CURDATE(), 1)";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int countTodayAttendancesByUser(Long userId) {
		String sql = "SELECT COUNT(*) FROM attendances WHERE DATE(start_date) = CURDATE() AND user_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, userId);
	}

	public int countWeekAttendancesByUser(Long userId) {
		String sql = "SELECT COUNT(*) FROM attendances WHERE YEARWEEK(DATE(start_date), 1) = YEARWEEK(CURDATE(), 1) AND user_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, userId);
	}

	public int getPerformancesStatistics() {
		String sql = "SELECT COUNT(*) FROM ( " +
					 "    SELECT user_id, COUNT(*) AS total_attendances " +
					 "    FROM attendances " +
					 "    GROUP BY user_id " +
					 "    HAVING total_attendances > ( " +
					 "        SELECT AVG(total_attendances) " +
					 "        FROM ( " +
					 "            SELECT COUNT(*) AS total_attendances " +
					 "            FROM attendances " +
					 "            GROUP BY user_id " +
					 "        ) AS attendance_counts " +
					 "    ) " +
					 ") AS users_above_average";

		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
