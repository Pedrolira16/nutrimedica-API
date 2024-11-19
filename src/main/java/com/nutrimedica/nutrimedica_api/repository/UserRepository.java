package com.nutrimedica.nutrimedica_api.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.nutrimedica.nutrimedica_api.dto.User;
import com.nutrimedica.nutrimedica_api.dto.Doctor;
import com.nutrimedica.nutrimedica_api.dto.Receptionist;
import com.nutrimedica.nutrimedica_api.dto.Specialty;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(
                sql,
                ps -> ps.setLong(1, id),
                rs -> rs.next()
                        ? Optional.of(new User(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("cpf"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("cellphone"),
                                rs.getString("cellphone_alternative")))
                        : Optional.empty());
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (name, cpf, email, password, cellphone, cellphone_alternative) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getCpf(), user.getEmail(), user.getPassword(),
                user.getCellphone(), user.getCellphoneAlternative());
    }

    public User getUser(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[] { email },
                (rs, rowNum) -> new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("cellphone"),
                        rs.getString("cellphone_alternative")));
    }

    public List<User> getUsers() {
        String sql = "SELECT u.*, d.council_name, d.council_state, d.council_number, r.shift, s.name AS specialty_name " +
                     "FROM users u " +
                     "LEFT JOIN doctors d ON u.id = d.user_id " +
                     "LEFT JOIN receptionists r ON u.id = r.user_id " +
                     "LEFT JOIN doctor_specialties ds ON ds.doctor_id = d.user_id " +
                     "LEFT JOIN specialties s ON ds.specialty_id = s.id";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> {
                User user = new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("cellphone"),
                    rs.getString("cellphone_alternative"));

                if (rs.getString("council_name") != null) {
                    Doctor doctor = new Doctor(
                        rs.getLong("id"),
                        rs.getString("council_name"),
                        rs.getString("council_state"),
                        rs.getString("council_number"));

                    String specialtyName = rs.getString("specialty_name");
                    if (specialtyName != null) {
                        Specialty specialty = new Specialty(specialtyName);
                        doctor.setSpecialty(specialty);
                    }
                    user.setDoctor(doctor);
                }

                if (rs.getString("shift") != null) {
                    Receptionist receptionist = new Receptionist(
                        rs.getLong("id"),
                        rs.getString("shift"));
                    user.setReceptionist(receptionist);
                }
                return user;
            });
    }

    public List<User> getUsersDoctors() {
        String sql = "SELECT u.*, d.council_name, d.council_state, d.council_number, s.name AS specialty_name " +
                     "FROM users u " +
                     "LEFT JOIN doctors d ON u.id = d.user_id " +
                     "LEFT JOIN doctor_specialties ds ON ds.doctor_id = d.user_id " +
                     "LEFT JOIN specialties s ON ds.specialty_id = s.id " +
                     "WHERE d.council_name IS NOT NULL";
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> {
                User user = new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("cellphone"),
                    rs.getString("cellphone_alternative"));

                Doctor doctor = new Doctor(
                    rs.getLong("id"),
                    rs.getString("council_name"),
                    rs.getString("council_state"),
                    rs.getString("council_number"));

                String specialtyName = rs.getString("specialty_name");
                if (specialtyName != null) {
                    System.out.println("Specialty found: " + specialtyName);
                    Specialty specialty = new Specialty(specialtyName);
                    doctor.setSpecialty(specialty);
                }
                user.setDoctor(doctor);
                return user;
            });
    }


    public List<User> getUsersReceptionists() {
        String sql = "SELECT u.*, r.shift " +
                "FROM users u LEFT JOIN receptionists r ON u.id = r.user_id " +
                "WHERE r.shift IS NOT NULL";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    User user = new User(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("cellphone"),
                            rs.getString("cellphone_alternative"));
                    Receptionist receptionist = new Receptionist(
                            rs.getLong("id"),
                            rs.getString("shift"));
                    user.setReceptionist(receptionist);
                    return user;
                });
    }

    public void deleteUser(Long id) {
        String deleteDoctorSql = "DELETE FROM doctors WHERE user_id = ?";
        jdbcTemplate.update(deleteDoctorSql, id);

        String deleteReceptionistSql = "DELETE FROM receptionists WHERE user_id = ?";
        jdbcTemplate.update(deleteReceptionistSql, id);

        String deleteUserSql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(deleteUserSql, id);
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, cpf = ?, email = ?, password = ?, cellphone = ?, cellphone_alternative = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getCpf(), user.getEmail(), user.getPassword(),
                user.getCellphone(), user.getCellphoneAlternative(), user.getId());
    }
    public User getUserDetail(Long userId) {
        String sql = "SELECT u.*, d.council_name, d.council_state, d.council_number, s.name AS specialty_name " +
                    "FROM users u " +
                    "LEFT JOIN doctors d ON u.id = d.user_id " +
                    "LEFT JOIN doctor_specialties ds ON ds.doctor_id = d.user_id " +
                    "LEFT JOIN specialties s ON ds.specialty_id = s.id " +
                    "WHERE u.id = ?";
        // Modifiquei para retornar um único User
        List<User> users = jdbcTemplate.query(
            sql,
            ps -> ps.setLong(1, userId),
            (rs, rowNum) -> {
                User user = new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("cellphone"),
                    rs.getString("cellphone_alternative"));

                if (rs.getString("council_name") != null) {
                    Doctor doctor = new Doctor(
                        rs.getLong("id"),
                        rs.getString("council_name"),
                        rs.getString("council_state"),
                        rs.getString("council_number"));

                    String specialtyName = rs.getString("specialty_name");
                    if (specialtyName != null) {
                        Specialty specialty = new Specialty(specialtyName);
                        doctor.setSpecialty(specialty);
                    }
                    user.setDoctor(doctor);
                }
                return user;
        });
        return users.isEmpty() ? null : users.get(0);  // Retorna o primeiro usuário encontrado ou null
    }
}
