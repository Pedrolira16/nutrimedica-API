package com.nutrimedica.nutrimedica_api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;


public class Patient {

	private Long id;

	@NotNull
	@Size(max = 250)
	private String name;

	@NotNull
	@Size(max = 25)
	private String cpf;

	@NotNull
	@Email
	@Size(max = 250)
	private String email;

	@NotNull
	@Size(max = 25)
	private String cellphone;

	@NotNull
	private LocalDate bornDate;

	private String observation;

	@Size(max = 25)
	private String bloodType;

	public Patient(Long id, String name, String cpf, String email, String cellphone, LocalDate bornDate, String observation, String bloodType) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.cellphone = cellphone;
		this.bornDate = bornDate;
		this.observation = observation;
		this.bloodType = bloodType;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public LocalDate getBornDate() {
		return bornDate;
	}

	public void setBornDate(LocalDate bornDate) {
		this.bornDate = bornDate;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
}