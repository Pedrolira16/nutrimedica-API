package com.nutrimedica.nutrimedica_api.dto;

public class Receptionist {
	private Long userId;
	private String shift;

	public Receptionist(Long userId, String shift) {
		this.userId = userId;
		this.shift = shift;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
}
