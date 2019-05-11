package ru.itis.darZam.repository;


import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.darZam.models.BankAccount;
import ru.itis.darZam.models.Icon;
import ru.itis.darZam.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@NoArgsConstructor
@Data
@org.springframework.stereotype.Repository
public class BankAcoountJDBCImpl implements RepositoryDao<BankAccount> {


    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(BankAccountRepository.class.getName());
    //language=SQL
    private static final String SQL_SELECT_ALL_BANK_ACC_BY_USER_ID =
            "Select bank_account.id, balance, bank_user_id,name, bank_account.up_date, bank_account.up_sum, i.path, icon_id " +
                    "From bank_account LEFT JOIN icon i on bank_account.icon_id = i.id " +
                    "WHERE bank_user_id=?; ";

    //language=SQL
    private static final String SQL_DELETE_ALL_BY_USER_ID = "DELETE FROM bank_account WHERE bank_user_id=?";


    //language=SQL
    private static final String SQL_FIND_ALL_BANK_ACCOUNT = "Select bank_account.id, balance, name, bank_user_id, bank_account.up_date, bank_account.up_sum, i.path, icon_id " +
            "From bank_account LEFT JOIN icon i on bank_account.icon_id = i.id ";

    //language=SQL
    private static final String SQL_DELETE_BANK_ACCOUNT_BY_ID = "DELETE FROM bank_account WHERE id=? ";

    //language=SQL
    private static final String SQL_SAVE_BANK_ACCOUNT = "INSERT INTO bank_account (balance, bank_user_id, up_sum, up_date, icon_id, name) " +
            " VALUES (?,?,?,?,?,?)";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "Select bank_account.id,name, balance, bank_user_id, bank_account.up_date, bank_account.up_sum, i.path, icon_id " +
            "From bank_account LEFT JOIN icon i on bank_account.icon_id = i.id WHERE bank_account.id=?";


    //language=SQL
    private static final String SQL_UPDATE_BANK_ACCOUNT = "UPDATE bank_account SET balance=?, up_date=?, up_sum=? WHERE id=?";

    @Autowired
    public BankAcoountJDBCImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private org.springframework.jdbc.core.RowMapper<BankAccount> bankAccountRowMapper = (resultSet, i) -> {
        BankAccount bankAccount = BankAccount.builder()
                .bankAccounNumber(resultSet.getLong("id"))
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .icon(Icon.builder()
                        .path(resultSet.getString("path"))
                        .id(resultSet.getLong("icon_id"))
                        .build())
                .balance(resultSet.getFloat("count"))
                .user(User.builder().id(resultSet.getLong("bank_user_id")).build())
                .upDate(resultSet.getDate("up_date"))
                .upSum(resultSet.getFloat("up_sum"))
                .build();
        return bankAccount;
    };

    @Override
    public Optional<BankAccount> findOne(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, bankAccountRowMapper, id));
        } catch (DataAccessException e) {
            this.logger.warning(String.format("%s exception in findOne", e));
            return Optional.empty();
        }
    }

    @Override
    public void save(BankAccount bankAccount) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement preparedStatement =
                                connection.prepareStatement(SQL_SAVE_BANK_ACCOUNT, new String[]{"id"});
                        preparedStatement.setDouble(1, bankAccount.getCount());
                        preparedStatement.setLong(2, bankAccount.getUser().getId());
                        if (bankAccount.getUpSum() <= 0) {
                            preparedStatement.setNull(4, Types.DATE);
                            preparedStatement.setNull(3, Types.FLOAT);
                        } else {
                            preparedStatement.setDate(4, bankAccount.getUpDate());
                            preparedStatement.setFloat(3, bankAccount.getUpSum());
                        }
                        if (bankAccount.getIcon() != null) {
                            preparedStatement.setLong(5, bankAccount.getIcon().getId());
                        } else {
                            preparedStatement.setNull(5, Types.VARCHAR);
                        }
                        if (bankAccount.getName() != null) {
                            preparedStatement.setString(6, bankAccount.getName());
                        } else {
                            preparedStatement.setNull(6, Types.VARCHAR);
                        }
                        return preparedStatement;
                    }, keyHolder);

            bankAccount.setId(keyHolder.getKey().longValue());
        } catch (DataAccessException e) {
            this.logger.warning(String.format("%s exception in save", e));
        }
    }

    @Override
    public void delete(Long id) {
        try {
            jdbcTemplate.update(SQL_DELETE_BANK_ACCOUNT_BY_ID, id);
        } catch (DataAccessException e) {
            this.logger.warning(String.format("%s exception in delete", e));
        }
    }

    @Override
    public List<BankAccount> findAll() {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BANK_ACCOUNT, bankAccountRowMapper);
        }catch (DataAccessException e) {
            this.logger.warning(String.format("%s exception in findAll", e));
            return new ArrayList<>();
        }

    }

    @Override
    public void update(BankAccount model) {
        try {
            jdbcTemplate.update(SQL_UPDATE_BANK_ACCOUNT, model.getCount(), model.getUpDate(), model.getUpSum(), model.getId());
        } catch (DataAccessException e) {
            this.logger.warning(String.format("%s exception in update", e));
        }
    }

    public List<BankAccount> findAllByUserId(Long id) {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL_BANK_ACC_BY_USER_ID, bankAccountRowMapper, id);
        } catch (DataAccessException e) {
            this.logger.warning(String.format("%s exception in findAllByUserId", e));
            return new ArrayList<>();
        }
    }

    public void deleteAllByUserId(Long id) {
        try {
            jdbcTemplate.update(SQL_DELETE_ALL_BY_USER_ID, id);
        } catch (DataAccessException e) {
            this.logger.warning(String.format("%s exception in delete by id", e));
        }

    }

}
