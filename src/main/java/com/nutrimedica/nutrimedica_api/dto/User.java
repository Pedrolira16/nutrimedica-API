package com.nutrimedica.nutrimedica_api.dto;

import javax.validation.constraints.*;

public class User {
    private Long id;

    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    @NotEmpty(message = "CPF é obrigatório")
    @Pattern(regexp = "^[0-9]{11}$", message = "CPF inválido")
    private String cpf;

    @Email(message = "Email inválido")
    @NotEmpty(message = "Email é obrigatório")
    private String email;

    @NotEmpty(message = "Senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String password;

    @NotEmpty(message = "Celular é obrigatório")
    private String cellphone;

    private String specialty;
    private String councilName;
    private String councilState;
    private String councilNumber;

    public User(Long id, String name, String cpf, String email, String password, String cellphone, String specialty,
                String councilName, String councilState, String councilNumber) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.specialty = specialty;
        this.councilName = councilName;
        this.councilState = councilState;
        this.councilNumber = councilNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getCouncilName() {
        return councilName;
    }

    public String getCouncilState() {
        return councilState;
    }

    public String getCouncilNumber() {
        return councilNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setCouncilName(String councilName) {
        this.councilName = councilName;
    }

    public void setCouncilState(String councilState) {
        this.councilState = councilState;
    }

    public void setCouncilNumber(String councilNumber) {
        this.councilNumber = councilNumber;
    }
}
