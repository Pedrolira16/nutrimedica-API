package com.nutrimedica.nutrimedica_api.dto;

import java.util.Date;
import jakarta.validation.constraints.*;

public class Attendance {
	private Long id;
	private Long patientId;
	private Long userId;
	private Long placeId;
	private Long agendaEventId;
	private boolean done;
	private Date startDate;
	private Date endDate;
	private String observation;

	public Attendance(Long id, Long patientId, Long userId, Long placeId, Long agendaEventId, boolean done, Date startDate, Date endDate, String observation) {
		this.id = id;
		this.patientId = patientId;
		this.userId = userId;
		this.placeId = placeId;
		this.agendaEventId = agendaEventId;
		this.done = done;
		this.startDate = startDate;
		this.endDate = endDate;
		this.observation = observation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public Long getAgendaEventId() {
		return agendaEventId;
	}

	public void setAgendaEventId(Long agendaEventId) {
		this.agendaEventId = agendaEventId;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}
