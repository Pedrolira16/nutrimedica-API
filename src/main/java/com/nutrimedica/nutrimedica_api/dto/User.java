package com.nutrimedica.nutrimedica_api.dto;

import jakarta.validation.constraints.*;
import com.nutrimedica.nutrimedica_api.dto.Doctor;
import com.nutrimedica.nutrimedica_api.dto.Receptionist;

public class User {
    private Doctor doctor;
    private Receptionist receptionist;

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

    private String cellphoneAlternative;

    public User(long id, String name, String cpf, String email, String password, String cellphone,
                String cellphoneAlternative) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.cellphoneAlternative = cellphoneAlternative;
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

    public String getCellphoneAlternative() {
        return cellphoneAlternative;
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

    public void setCellphoneAlternative(String cellphoneAlternative) {
        this.cellphoneAlternative = cellphoneAlternative;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }
}
