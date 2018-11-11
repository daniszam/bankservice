package repositories;

import models.Cash;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class CashRepository implements Repository<Cash> {

    private JdbcTemplate jdbcTemplate;


    //language=SQL
    public static final String SQL_INSERT_CASH = "INSERT INTO cash (user_id, balance) VALUES (?,?)";

    //language=SQL
    public static final String SQL_SELECT_ALL = "SELECT * FROM cash";

    //language=SQL
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM cash WHERE cash.id=?";

    public CashRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Cash> cashRowMapper = ((resultSet, i) -> Cash.builder()
            .balance(resultSet.getFloat("balance"))
            .user(User.builder().id(resultSet.getLong("user_id")).build())
            .build());


    @Override
    public Optional<Cash> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,cashRowMapper, id));
    }

    @Override
    public boolean save(Cash model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(SQL_INSERT_CASH, new String[] {"id"});
                    preparedStatement.setLong(1, model.getUser().getId());
                    preparedStatement.setFloat(2, model.getBalance());
                    return preparedStatement;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
        return true;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Cash> findAll() {
        return null;
    }
}
