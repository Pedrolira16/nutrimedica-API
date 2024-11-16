package com.nutrimedica.nutrimedica_api.dto;

import jakarta.validation.constraints.*;

public class Place {

	private Long id;

	@NotNull
	@Size(max = 250)
	private String name;

	@NotNull
	@Size(max = 25)
	private String addressCep;

	@NotNull
	@Size(max = 250)
	private String address;

	@NotNull
	@Size(max = 25)
	private String addressNumber;

	@NotNull
	@Size(max = 250)
	private String addressDistrict;

	@NotNull
	@Size(max = 250)
	private String addressCity;

	@NotNull
	@Size(max = 25)
	private String addressState;

	@Size(max = 250)
	private String addressComplement;

	public Place(Long id, String name, String addressCep, String address, String addressNumber, String addressDistrict, String addressCity, String addressState, String addressComplement) {
		this.id = id;
		this.name = name;
		this.addressCep = addressCep;
		this.address = address;
		this.addressNumber = addressNumber;
		this.addressDistrict = addressDistrict;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressComplement = addressComplement;
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

	public String getAddressCep() {
		return addressCep;
	}

	public void setAddressCep(String addressCep) {
		this.addressCep = addressCep;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressDistrict() {
		return addressDistrict;
	}

	public void setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}
}
