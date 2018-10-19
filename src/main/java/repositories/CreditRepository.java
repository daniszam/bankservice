package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Credit;
import models.Insurance;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreditRepository implements Repository<Credit>, AllByIDRepository<Credit> {

    private Connection connection;


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

    public CreditRepository (Connection connection){
        this.connection = connection;
    }


    private RowMapper<Credit> creditRowMapper = new RowMapper<Credit>() {
        @Override
        @SneakyThrows
        public Credit rowMap(ResultSet resultSet) {
            BankUserRepository bankUserRepository = new BankUserRepository(connection);
            Optional<User> user = bankUserRepository.findOne(resultSet.getLong("bank_user_id"));
            Credit credit = Credit.builder()
                    .type(resultSet.getString("type_credit"))
                    .expirationDate(resultSet.getDate("expiration_date"))
                    .user(user.get())
                    .percent(resultSet.getFloat("perzent"))
                    .id(resultSet.getLong("id"))
                    .build();
            return credit;
        }
    };

    @Override
    @SneakyThrows
    public List<Credit> findAllByUserId(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SEARCH_CREDIT_BY_USER_ID);
        preparedStatement.setLong(1, id);
        if (preparedStatement.execute()) {
            List<Credit>  credits = new ArrayList<>();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                credits.add(creditRowMapper.rowMap(resultSet));
                return credits;
            }
        }
        return null;
    }

    @Override
    @SneakyThrows
    public void deleteAllByUserId(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ALL_BY_USER_ID);
        preparedStatement.setLong(1,id);
        preparedStatement.executeUpdate();
    }

    @Override
    @SneakyThrows
    public Optional<Credit> findOne(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
        preparedStatement.setLong(1, id);
        if (preparedStatement.execute()) {
            ResultSet resultSet = preparedStatement.getResultSet();
            return Optional.of(creditRowMapper.rowMap(resultSet));
        }
        return null;
    }

    @Override
    @SneakyThrows
    public boolean save(Credit credit) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CREDIT);
        preparedStatement.setLong(1,credit.getUser().getId());
        preparedStatement.setString(2,credit.getType());
        preparedStatement.setDate(3, credit.getExpirationDate());
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID_FROM_CREDIT);
        preparedStatement.setLong(1,id);
        preparedStatement.executeUpdate();
    }

    @Override
    @SneakyThrows
    public List<Credit> findAll() {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Credit> credits = new ArrayList<>();
            while (resultSet.next()){
                credits.add(creditRowMapper.rowMap(resultSet));
            }
            return credits;
        }
        return null;
    }

    @SneakyThrows
    public List<String> getType(){
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TYPE_CREDIT);
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            List<String> items = new ArrayList<>();
            while(resultSet.next()){
                items.add(resultSet.getString("type_credit"));
            }
            return items;
        }
        return null;
    }
}
