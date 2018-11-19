package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Credit;
import models.Insurance;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreditRepository implements Repository<Credit>, AllByIDRepository<Credit> {

    private JdbcTemplate jdbcTemplate;


    //language=SQL
    public static final String SQL_SEARCH_CREDIT_BY_USER_ID = "SELECT * FROM credit JOIN credit_type c2 on credit.type_credit = c2.type_credit" +
            " WHERE credit.bank_user_id = ?";

    //language=SQL
    public static final String SQL_DELETE_ALL_BY_USER_ID = "DELETE FROM credit WHERE bank_user_id=?";

    //language=SQL
    public static final String SQL_FIND_ALL = "SELECT * FROM credit JOIN credit_type c2 on credit.type_credit = c2.type_credit";

    //language=SQL
    public static final String SQL_FIND_BY_ID = "SELECT * FROM credit JOIN credit_type c2 on credit.type_credit = c2.type_credit WHERE id=?";

    //language=SQL
    public static final String SQL_DELETE_BY_ID_FROM_CREDIT = "DELETE FROM credit WHERE id=? ";

    //language=SQL
    public static final String SQL_INSERT_INTO_CREDIT = "INSERT INTO credit (bank_user_id, type_credit, expiration_date) " +
            " VALUES (?,?,?)";


    //language=SQL
    public static final String SQL_GET_TYPE_CREDIT = "SELECT type_credit FROM credit_type";

    public CreditRepository (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private org.springframework.jdbc.core.RowMapper<Credit> creditRowMapper = ((resultSet, i) -> Credit.builder()
            .type(resultSet.getString("type_credit"))
            .expirationDate(resultSet.getDate("expiration_date"))
            .user(User.builder().id(resultSet.getLong("bank_user_id")).build())
            .percent(resultSet.getFloat("percent"))
            .id(resultSet.getLong("id"))
            .build());

    @Override
    @SneakyThrows
    public List<Credit> findAllByUserId(Long id) {
        return jdbcTemplate.query(SQL_SEARCH_CREDIT_BY_USER_ID, creditRowMapper);
    }

    @Override
    @SneakyThrows
    public void deleteAllByUserId(Long id) {
        jdbcTemplate.update(SQL_DELETE_ALL_BY_USER_ID);
    }

    @Override
    @SneakyThrows
    public Optional<Credit> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, creditRowMapper));

    }

    @Override
    @SneakyThrows
    public boolean save(Credit credit) {
        jdbcTemplate.update(SQL_INSERT_INTO_CREDIT, creditRowMapper);
        return true;
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID_FROM_CREDIT);
    }

    @Override
    @SneakyThrows
    public List<Credit> findAll() {
       return jdbcTemplate.query(SQL_FIND_ALL, creditRowMapper);
    }

    @Override
    public void update(Credit model) {

    }

}
