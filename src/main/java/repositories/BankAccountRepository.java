package repositories;

import lombok.SneakyThrows;
import models.BankAccount;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class BankAccountRepository implements Repository<BankAccount>, AllByIDRepository<BankAccount> {


    private JdbcTemplate jdbcTemplate;

    //language=SQL
    public static final String SQL_SELECT_ALL_BANK_ACC_BY_USER_ID =
            "Select bank_account.id, balance,type_bank_account, code_word, percent, bank_user_id " +
            "From bank_account " +
            "JOIN type_bank_account t on bank_account.type_bank_account = t.name " +
            "WHERE bank_user_id=?; ";

    //language=SQL
    public static final String SQL_DELETE_ALL_BY_USER_ID = "DELETE FROM bank_account WHERE bank_user_id=?";


    //language=SQL
    public static final String SQL_FIND_ALL_BANK_ACCOUNT = "SELECT * FROM bank_account JOIN  type_bank_account a on bank_account.type_bank_account = a.name";

    //language=SQL
    public static final String SQL_DELETE_BANK_ACCOUNT_BY_ID = "DELETE FROM bank_account WHERE id=? ";

    //language=SQL
    public static final String SQL_SAVE_BANK_ACCOUNT = "INSERT INTO bank_account (balance, bank_user_id, " +
            "type_bank_account, code_word) VALUES (?,?,?,?)";

    //language=SQL
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM bank_account JOIN type_bank_account a on " +
            "bank_account.type_bank_account = a.name WHERE bank_account.id=?";


    //language=SQL
    public static final String SQL_GET_TYPE_BANK_ACCOUNT = "SELECT name FROM type_bank_account";

    public BankAccountRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    private org.springframework.jdbc.core.RowMapper<BankAccount> bankAccountRowMapper = (resultSet, i) -> {
        BankAccount bankAccount = BankAccount.builder()
                .bankAccounNumber(resultSet.getLong("id"))
                .balance(resultSet.getFloat("balance"))
                .typeBankAccount(resultSet.getString("type_bank_account"))
                .codeWord(resultSet.getString("code_word"))
                .percent(resultSet.getFloat("percent"))
                .user(User.builder().id(resultSet.getLong("bank_user_id")).build())
                .build();
        return bankAccount;
    };

    @Override
    @SneakyThrows
    public Optional<BankAccount> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,bankAccountRowMapper, id));
    }

    @Override
    @SneakyThrows
    public boolean save(BankAccount bankAccount) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(SQL_SAVE_BANK_ACCOUNT, new String[] {"id"});
                    preparedStatement.setDouble(1, bankAccount.getBalance());
                    preparedStatement.setLong(2,bankAccount.getUser().getId());
                    preparedStatement.setString(3, bankAccount.getTypeBankAccount());
                    preparedStatement.setString(4, bankAccount.getCodeWord());
                    return preparedStatement;
                }, keyHolder);

        bankAccount.setId(keyHolder.getKey().longValue());
        return true;
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BANK_ACCOUNT_BY_ID, id);
    }

    @Override
    @SneakyThrows
    public List<BankAccount> findAll(){
        return jdbcTemplate.query(SQL_FIND_ALL_BANK_ACCOUNT, bankAccountRowMapper);

    }

    @SneakyThrows
    public List<BankAccount> findAllByUserId(Long id){
        return  jdbcTemplate.query(SQL_SELECT_ALL_BANK_ACC_BY_USER_ID, bankAccountRowMapper, id);
    }

    @SneakyThrows
    @Override
    public void deleteAllByUserId(Long id) {
        jdbcTemplate.update(SQL_DELETE_ALL_BY_USER_ID, id);

    }

}
