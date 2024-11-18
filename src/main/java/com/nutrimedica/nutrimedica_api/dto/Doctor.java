package com.nutrimedica.nutrimedica_api.dto;

public class Doctor {
	private Long userId;
	private String councilName;
	private String councilState;
	private String councilNumber;


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
}
