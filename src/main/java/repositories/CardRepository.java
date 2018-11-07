package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Card;
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
            "Select id,bank_user_id,card.type_card, bank_account_id, " +
               " t.perzent, t.validity_period, card.balance " +
            "From card " +
            "JOIN card_type t on card.type_card = t.type_card " +
            "WHERE bank_user_id=?;";

    //language=SQL
    public static final String SQL_INSERT_INTO_CARD = "INSERT INTO card (bank_user_id, type_card) " +
            "VALUES (?, ?) ";

    //language=SQL
    public static final String SQL_DELETE_CARD = "DELETE FROM card WHERE id=?";

    //language=SQL
    public static final String SQL_FIND_ALL_CARD = "SELECT * FROM card JOIN card_type c2 on card.type_card = c2.type_card";

    //language=SQL
    public static final String SQL_GET_TYPE_CARD = "SELECT type_card FROM card_type";



    //language=SQL
    public static final String SQL_DELEATE_ALL_BY_USER_ID = "DELETE FROM card WHERE bank_user_id=?";
    public CardRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


  private org.springframework.jdbc.core.RowMapper<Card> cardRowMapper = ((resultSet, i) -> Card.builder()
            .cardType(resultSet.getString("type_card"))
            .perzent(resultSet.getFloat("perzent"))
            .user(User.builder()
                    .id(resultSet.getLong("bank_user_id"))
                    .build())
            .validityPeriod( resultSet.getString("validity_period"))
            .id(resultSet.getLong("id"))
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
                            connection.prepareStatement(SQL_INSERT_INTO_CARD, new String[] {"id"});
                    preparedStatement.setLong(1, card.getUser().getId());
                    preparedStatement.setString(2, card.getCardType());
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
    public List<Card> findAllByUserId(Long id){
        return jdbcTemplate.query(SQL_SELECT_ALL_CARD_BY_ID, cardRowMapper, id);
    }

    @SneakyThrows
    @Override
    public void deleteAllByUserId(Long id) {
        jdbcTemplate.update(SQL_DELEATE_ALL_BY_USER_ID, id);
    }


}
