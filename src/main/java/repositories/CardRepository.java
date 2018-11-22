package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Card;
import models.Icon;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepository implements Repository<Card>, AllByIDRepository<Card> {


    private JdbcTemplate jdbcTemplate;

    //language=SQL
    public static final String SQL_SELECT_ALL_CARD_BY_ID =
            "Select card.id,bank_user_id, " +
                    " card.up_sum, card.up_date, card.balance, card.name, i.id AS icon_id, i.path, card.id " +
                    "From card LEFT JOIN icon i on card.icon_id = i.id " +
                    "WHERE bank_user_id=?;";

    //language=SQL
    public static final String SQL_INSERT_INTO_CARD = "INSERT INTO card (bank_user_id, balance, up_date, up_sum, name, icon_id) " +
            "VALUES (?, ?, ?, ?, ?,?) ";

    //language=SQL
    public static final String SQL_DELETE_CARD = "DELETE FROM card WHERE id=?";

    //language=SQL
    public static final String SQL_FIND_ALL_CARD = "SELECT card.bank_user_id, card.id, i.id AS icon_id, i.path,card.name, card.up_date,card.up_sum,card.balance" +
            " FROM card LEFT JOIN icon i on card.icon_id = i.id";

    //language=SQL
    public static final String SQL_UPDATE_CARD = "UPDATE card SET balance=?, up_date=?, up_sum=? WHERE id=?";

    //


    //language=SQL
    public static final String SQL_DELEATE_ALL_BY_USER_ID = "DELETE FROM card WHERE bank_user_id=?";

    public CardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private org.springframework.jdbc.core.RowMapper<Card> cardRowMapper = ((resultSet, i) -> Card.builder()
            .user(User.builder()
                    .id(resultSet.getLong("bank_user_id"))
                    .build())
            .icon(Icon.builder()
                    .id(resultSet.getLong("icon_id"))
                    .path(resultSet.getString("path"))
                    .build())
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .upSum(resultSet.getFloat("up_sum"))
            .upDate(resultSet.getDate("up_date"))
            .balance(resultSet.getFloat("balance"))
            .build());


    @Override
    public Optional<Card> findOne(Long id) {
        return null;
    }

    @Override
    @SneakyThrows
    public boolean save(Card card) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(SQL_INSERT_INTO_CARD, new String[]{"id"});
                    preparedStatement.setLong(1, card.getUser().getId());
                    preparedStatement.setFloat(2, card.getBalance());
                    if (card.getUpSum() <= 0) {
                        preparedStatement.setNull(3, Types.DATE);
                        preparedStatement.setNull(4, Types.FLOAT);
                    } else {
                        preparedStatement.setDate(3, card.getUpDate());
                        preparedStatement.setFloat(4, card.getUpSum());
                    }
                    if (card.getName() != null) {
                        preparedStatement.setString(5, card.getName());
                    } else {
                        preparedStatement.setNull(5, Types.VARCHAR);
                    }
                    if (card.getIcon() != null) {
                        preparedStatement.setLong(6, card.getIcon().getId());
                    } else {
                        preparedStatement.setNull(6, Types.VARCHAR);
                    }
                    return preparedStatement;
                }, keyHolder);

        card.setId(keyHolder.getKey().longValue());
        return true;
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_CARD, id);
    }

    @Override
    @SneakyThrows
    public List<Card> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_CARD, cardRowMapper);
    }

    @SneakyThrows
    public List<Card> findAllByUserId(Long id) {
        return jdbcTemplate.query(SQL_SELECT_ALL_CARD_BY_ID, cardRowMapper, id);
    }

    @SneakyThrows
    @Override
    public void deleteAllByUserId(Long id) {
        jdbcTemplate.update(SQL_DELEATE_ALL_BY_USER_ID, id);
    }

    public void update(Card card) {
        jdbcTemplate.update(SQL_UPDATE_CARD, card.getBalance(), card.getUpDate(), card.getUpSum(), card.getId());

    }


}
