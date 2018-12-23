package repositories;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import mappers.RowMapper;
import models.*;
import models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@NoArgsConstructor
public class TransactionRepository implements Repository<Transaction> {


    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SEARCH_ACCOUNT_BALANCE = "SELECT balance FROM bank_account WHERE id=?";

    //language=SQL
    private static final String SQL_TRANSFER_FROM_ACCOUNT = "UPDATE bank_account SET balance=balance - ? WHERE id=?";

    //language=SQL
    private static final String SQL_TRANSFER_TO_ACCOUNT = "UPDATE bank_account SET balance=balance + ? WHERE id=?";

    //language=SQL
    private static final String SQL_INSERT_TRANSACTION = "INSERT INTO transaction (date_time, user_id, transfer, category_id) " +
            "VALUES (?,?,?,?) " ;


    //language=SQL
    private static final String SQL_DELETE_TRANSACTION = "DELETE FROM transaction Where id = ?";

    //language=SQL
    private static final String SQL_ALL_TRANSACTION = "SELECT * FROM transaction" ;

    //language=SQL
    private static final String SQL_DELETE_BY_ACCOUNT_ID = "DELETE FROM transaction WHERE type_id=? OR user_id=?";

    //language=SQL
    private static final String SQL_INSERT_INTO_TRANSACTION_BY_CARD = "INSERT INTO transaction_by_card (transaction_id, card_id) VALUES (?,?)";

    //language=SQL
    private static final String SQL_UPDATE_CARD = "UPDATE card SET balance=? WHERE id=?";

    //language=SQL
    private static final String SQL_INSERT_INTO_TRANSACTION_BY_BANK_ACCOUNT = "INSERT INTO transaction_by_bank_account (transaction_id, bank_account_id) VALUES (?,?)";

    //language=SQL
    private static final String SQL_UPDATE_BANK_ACCOUNT = "UPDATE bank_account SET balance=? WHERE id=?";

    //language=SQL
    private static final String SQL_INSERT_INTO_TRANSACTION_BY_CASH = "INSERT INTO transaction_by_cash (transaction_id, cash_id) VALUES (?,?)";

    //language=SQL
    private static final String SQL_UPDATE_CASH = "UPDATE cash SET balance=? WHERE id=?";

    //language=SQL
    public static final String SQL_SELECT_TRANSACTION_BY_USER_ID = "SELECT transaction.id AS transaction_id, transfer, date_time, color, c2.name AS category_name, category_id, user_id FROM transaction LEFT JOIN category c2 on transaction.category_id = c2.id WHERE user_id=?";





    public TransactionRepository (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Transaction> transactionRowMapper = new RowMapper<Transaction>() {
        @Override
        @SneakyThrows
        public Transaction rowMap(ResultSet resultSet) {
            Transaction transaction = Transaction.builder()
                    .dateTime(resultSet.getDate("date_time"))
                    .price(resultSet.getFloat("transfer"))
                    .id(resultSet.getLong("id"))
                    .build();
            return transaction;
        }
    };

    private org.springframework.jdbc.core.RowMapper<Transaction> transactionRowMapperWithCategory = ((resultSet, i) ->
            Transaction.builder()
            .price(resultSet.getFloat("transfer"))
            .dateTime(resultSet.getDate("date_time"))
            .category(Category.builder()
                    .color(new Color(resultSet.getInt("color")))
                    .id(resultSet.getLong("category_id"))
                    .name(resultSet.getString("category_name"))
                    .build())
            .user(User.builder()
                    .id(resultSet.getLong("user_id"))
                    .build())
            .id(resultSet.getLong("transaction_id"))
            .build());

    @Override
    public Optional<Transaction> findOne(Long id) {
        return null;
    }

    @Override
    @SneakyThrows
    public boolean save(Transaction transaction) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(SQL_INSERT_TRANSACTION, new String[] {"id"});
                    preparedStatement.setDate(1, transaction.getDateTime());
                    preparedStatement.setLong(2, transaction.getUser().getId());
                    preparedStatement.setFloat(3, transaction.getPrice());
                    preparedStatement.setLong(4, transaction.getCategory().getId());
                    return preparedStatement;
                }, keyHolder);

        transaction.setId(keyHolder.getKey().longValue());
        return true;
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {

    }

    public void saveToBalance(Long transactionId, Balance balance){
        Class balanceType = balance.getClass();
        if(balanceType.equals(Card.class)){
            jdbcTemplate.update(SQL_INSERT_INTO_TRANSACTION_BY_CARD, transactionId, balance.getId());
            jdbcTemplate.update(SQL_UPDATE_CARD, balance.getBalance(), balance.getId());
        }
        if(balanceType.equals(BankAccount.class)){
            jdbcTemplate.update(SQL_INSERT_INTO_TRANSACTION_BY_BANK_ACCOUNT, transactionId, balance.getId());
            jdbcTemplate.update(SQL_UPDATE_BANK_ACCOUNT, balance.getBalance(), balance.getId());
        }
        if(balanceType.equals(Cash.class)){
            jdbcTemplate.update(SQL_INSERT_INTO_TRANSACTION_BY_CARD, transactionId, balance.getId());
            jdbcTemplate.update(SQL_UPDATE_CASH, balance.getBalance(), balance.getId());
        }
    }
    @Override
    @SneakyThrows
    public List<Transaction> findAll() {
return null;
    }

    public List<Transaction> findAllByUserId(User user){
        return jdbcTemplate.query(SQL_SELECT_TRANSACTION_BY_USER_ID, transactionRowMapperWithCategory, user.getId());
    }

    @Override
    public void update(Transaction model) {

    }


}
