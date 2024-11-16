package com.nutrimedica.nutrimedica_api.dto;

import jakarta.validation.constraints.*;

public class AgendaEvent {

	private Long id;

	@NotNull
	@Size(max = 250)
	private String name;

	@NotNull
	private Integer duration;

	@NotNull
	@Size(max = 25)
	private String paymentMethod;

	private String description;

	private Boolean isDeleted;

	public AgendaEvent(Long id, String name, Integer duration, String paymentMethod, String description) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.paymentMethod = paymentMethod;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
