package com.nutrimedica.nutrimedica_api.repository;

import com.nutrimedica.nutrimedica_api.dto.AgendaEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AgendaEventRepository {

	private final JdbcTemplate jdbcTemplate;

	public AgendaEventRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createAgendaEvent(AgendaEvent agendaEvent) {
		String sql = "INSERT INTO agenda_events (name, duration, payment_method, description)" +
					"VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sql, agendaEvent.getName(), agendaEvent.getDuration(), agendaEvent.getPaymentMethod(),
							agendaEvent.getDescription());
	}

	public List<AgendaEvent> getAgendaEvents() {
		String sql = "SELECT * FROM agenda_events";
		return jdbcTemplate.query(
			sql,
			(rs, rowNum) -> new AgendaEvent(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getInt("duration"),
				rs.getString("payment_method"),
				rs.getString("description")
			)
		);
	}

	public void deleteAgendaEvent(Long id) {
		String sql = "DELETE FROM agenda_events WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void updateAgendaEvent(AgendaEvent agendaEvent) {
		String sql = "UPDATE agenda_events SET name = ?, duration = ?, payment_method = ?, description = ? WHERE id = ?";
		jdbcTemplate.update(sql, agendaEvent.getName(), agendaEvent.getDuration(), agendaEvent.getPaymentMethod(),
							agendaEvent.getDescription(), agendaEvent.getId());
	}
}
