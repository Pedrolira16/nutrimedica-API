package com.nutrimedica.nutrimedica_api.dto;

import com.nutrimedica.nutrimedica_api.dto.Specialty;
public class Doctor {
	private Long userId;
	private String councilName;
	private String councilState;
	private String councilNumber;
	private Specialty specialty;

    public Doctor(long userId, String councilName, String councilState, String councilNumber) {
        this.userId = userId;
        this.councilName = councilName;
        this.councilState = councilState;
        this.councilNumber = councilNumber;

    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCouncilName() {
		return councilName;
	}

	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}

	public String getCouncilState() {
		return councilState;
	}

	public void setCouncilState(String councilState) {
		this.councilState = councilState;
	}

	public String getCouncilNumber() {
		return councilNumber;
	}

	public void setCouncilNumber(String councilNumber) {
		this.councilNumber = councilNumber;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
}
