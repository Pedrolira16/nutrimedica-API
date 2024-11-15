package com.nutrimedica.nutrimedica_api.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.nutrimedica.nutrimedica_api.dto.User;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (name, cpf, email, password, cellphone, specialty, council_name, council_state, council_number) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getCpf(), user.getEmail(), user.getPassword(),
                            user.getCellphone(), user.getSpecialty(), user.getCouncilName(),
                            user.getCouncilState(), user.getCouncilNumber());
    }
    public User getUser(String email) {
        String sql = "SELECT * FROM users WHERE email = :email";  // Parâmetro nomeado

        // Usando um map de parâmetros
        Map<String, Object> params = Map.of("email", email);

        // Executa a consulta usando o parâmetro nomeado
        List<User> users = namedParameterJdbcTemplate.query(
            sql,
            params,  // Parâmetros como mapa
            (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("cpf"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("cellphone"),
                rs.getString("specialty"),
                rs.getString("council_name"),
                rs.getString("council_state"),
                rs.getString("council_number")
            )
        );

        return users.isEmpty() ? null : users.get(0);  // Retorna o primeiro usuário ou null
    }
}
