package repositories;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import mappers.RowMapper;
import models.*;
import models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
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





    public TransactionRepository (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Transaction> transactionRowMapper = new RowMapper<Transaction>() {
        @Override
        @SneakyThrows
        public Transaction rowMap(ResultSet resultSet) {
            BankAccountRepository bankAccountRepository = new BankAccountRepository(jdbcTemplate.getDataSource());
            Optional<BankAccount> fromBankAcoount = bankAccountRepository.findOne(resultSet.getLong("from_account"));
            Optional<BankAccount> toBankAccount = bankAccountRepository.findOne(resultSet.getLong("to_account"));
            Transaction transaction = Transaction.builder()
                    .dateTime(resultSet.getDate("date_time"))
                    .price(resultSet.getFloat("transfer"))
                    .id(resultSet.getLong("id"))
                    .build();
            return transaction;
        }
    };

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
        String balanceType = balance.getClass().getSimpleName();
        if(balanceType.equals(Card.class.getSimpleName())){
            jdbcTemplate.update(SQL_INSERT_INTO_TRANSACTION_BY_CARD, transactionId, balance.getId());
            jdbcTemplate.update(SQL_UPDATE_CARD, balance.getBalance(), balance.getId());
        }
        if(balanceType.equals(BankAccount.class.getSimpleName())){
            jdbcTemplate.update(SQL_INSERT_INTO_TRANSACTION_BY_BANK_ACCOUNT, transactionId, balance.getId());
            jdbcTemplate.update(SQL_UPDATE_BANK_ACCOUNT, balance.getBalance(), balance.getId());
        }
        if(balanceType.equals(Cash.class.getSimpleName())){
            jdbcTemplate.update(SQL_INSERT_INTO_TRANSACTION_BY_CARD, transactionId, balance.getId());
            jdbcTemplate.update(SQL_UPDATE_CASH, balance.getBalance(), balance.getId());
        }
    }
    @Override
    @SneakyThrows
    public List<Transaction> findAll() {
return null;
    }

    @Override
    public void update(Transaction model) {

    }


}
