package com.nutrimedica.nutrimedica_api.service;

import com.nutrimedica.nutrimedica_api.dto.AgendaEvent;
import com.nutrimedica.nutrimedica_api.repository.AgendaEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendaEventService {

	private final AgendaEventRepository agendaEventRepository;

	@Autowired
	public AgendaEventService(AgendaEventRepository agendaEventRepository) {
		this.agendaEventRepository = agendaEventRepository;
	}

	public void createAgendaEvent(AgendaEvent agendaEvent) {
		agendaEventRepository.createAgendaEvent(agendaEvent);
	}

	public List<AgendaEvent> getAgendaEvents() {
		return agendaEventRepository.getAgendaEvents();
	}

	public void deleteAgendaEvent(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Evento de agenda não encontrado.");
		}

		agendaEventRepository.deleteAgendaEvent(id);
	}

	public void updateAgendaEvent(Long id, AgendaEvent agendaEvent) {
		agendaEvent.setId(id);
		agendaEventRepository.updateAgendaEvent(agendaEvent);
	}
}
