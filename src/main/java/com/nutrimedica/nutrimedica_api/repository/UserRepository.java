package com.nutrimedica.nutrimedica_api.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.nutrimedica.nutrimedica_api.dto.User;
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
                                rs.getString("cellphone_alternative"),
                                rs.getString("specialty"),
                                rs.getString("council_name"),
                                rs.getString("council_state"),
                                rs.getString("council_number")
                        ))
                        : Optional.empty()
        );
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (name, cpf, email, password, cellphone, cellphone_alternative, specialty, council_name, council_state, council_number) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getCpf(), user.getEmail(), user.getPassword(),
                            user.getCellphone(), user.getCellphoneAlternative(), user.getSpecialty(), user.getCouncilName(),
                            user.getCouncilState(), user.getCouncilNumber());
    }

    public User getUser(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(
            sql,
            new Object[]{email},
            (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("cpf"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("cellphone"),
                rs.getString("cellphone_alternative"),
                rs.getString("specialty"),
                rs.getString("council_name"),
                rs.getString("council_state"),
                rs.getString("council_number")
            )
        );
    }

    public List<User> getUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("cpf"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("cellphone"),
                rs.getString("cellphone_alternative"),
                rs.getString("specialty"),
                rs.getString("council_name"),
                rs.getString("council_state"),
                rs.getString("council_number")
            )
        );
    }

    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, cpf = ?, email = ?, password = ?, cellphone = ?, cellphone_alternative = ?, specialty = ?, council_name = ?, council_state = ?, council_number = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getCpf(), user.getEmail(), user.getPassword(),
                            user.getCellphone(), user.getCellphoneAlternative(), user.getSpecialty(), user.getCouncilName(),
                            user.getCouncilState(), user.getCouncilNumber(), user.getId());
    }
}
