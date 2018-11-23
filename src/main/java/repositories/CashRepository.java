package repositories;

import models.Cash;
import models.Icon;
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
    public static final String SQL_INSERT_CASH = "INSERT INTO cash (user_id, balance, icon_id) VALUES (?,?,?)";

    //language=SQL
    public static final String SQL_SELECT_ALL = "SELECT user_id,balance, cash.id,i.path, i.id AS icon_id FROM cash LEFT JOIN icon i on cash.icon_id = i.id";

    //language=SQL
    public static final String SQL_SELECT_BY_ID = "SELECT user_id,balance, cash.id,i.path, i.id AS icon_id FROM cash LEFT JOIN icon i on cash.icon_id = i.id WHERE cash.id=?";

    //language=SQL
    public static final String SQL_UPDATE_CASH = "UPDATE cash SET balance=? WHERE id=?";

    public CashRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Cash> cashRowMapper = ((resultSet, i) -> Cash.builder()
            .balance(resultSet.getFloat("balance"))
            .icon(Icon.builder()
                    .id(resultSet.getLong("icon_id"))
                    .path(resultSet.getString("path"))
                    .build())
            .user(User.builder().id(resultSet.getLong("user_id")).build())
            .build());


    @Override
    public Optional<Cash> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, cashRowMapper, id));
    }

    @Override
    public boolean save(Cash model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(SQL_INSERT_CASH, new String[]{"id"});
                    preparedStatement.setLong(1, model.getUser().getId());
                    preparedStatement.setFloat(2, model.getBalance());
                    if (model.getIcon() != null) {
                        preparedStatement.setLong(3, model.getIcon().getId());
                    } else {
                        preparedStatement.setNull(3, Types.BIGINT);
                    }
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

    @Override
    public void update(Cash model) {
        jdbcTemplate.update(SQL_UPDATE_CASH, model.getBalance(), model.getId());

    }
}
