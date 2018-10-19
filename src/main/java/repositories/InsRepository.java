package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.Insurance;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InsRepository implements Repository<Insurance>, AllByIDRepository<Insurance> {
    private Connection connection;


    public InsRepository(Connection connection){
        this.connection = connection;
    }

    //language=SQL
    public static final String SQL_SEARCH_INSURANCE_BY_USER_ID = "SELECT * FROM insurance JOIN insured_person ip on insurance.id = ip.id " +
            "WHERE ip.bank_user_id = ?";

    //language=SQL
    public static final String SQL_DELETE_ALL_BY_USER_ID = "DELETE FROM insured_person WHERE bank_user_id=?";

    //language=SQL
    public static final String SQL_FIND_ALL = "SELECT * FROM insurance JOIN insured_person ip on insurance.type_insurance = ip.insured_type";

    //language=SQL
    public static final String SQL_FIND_BY_ID = "SELECT * FROM insurance JOIN insured_person ip on insurance.id = ip.insurance_id WHERE id=?";

    //language=SQL
    public static final String SQL_DELETE_BY_ID_FROM_INS = "DELETE FROM insured_person WHERE id=? ";

    //language=SQL
    public static final String SQL_INSERT_INTO_INSURED_PERS = "INSERT INTO insured_person (bank_user_id, insured_type, start_date, final_date) " +
            " VALUES (?,?,?,?)";


    private RowMapper<Insurance> insuranceRowMapper = new RowMapper<Insurance>() {
        @Override
        @SneakyThrows
        public Insurance rowMap(ResultSet resultSet) {
            BankUserRepository bankUserRepository = new BankUserRepository(connection);
            Optional<User> user = bankUserRepository.findOne(resultSet.getLong("bank_user_id"));
            Insurance insurance = Insurance.builder()
                    .typeInsurance(resultSet.getString("type_insurance"))
                    .priceMonth(resultSet.getDouble("price_month"))
                    .id(resultSet.getLong("id"))
                    .startDate(resultSet.getDate("start_date"))
                    .finishDate(resultSet.getDate("finish_date"))
                    .user(user.get())
                    .build();
            return insurance;
        }
    };

    @Override
    @SneakyThrows
    public Optional<Insurance> findOne(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
        preparedStatement.setLong(1, id);
        if (preparedStatement.execute()) {
            ResultSet resultSet = preparedStatement.getResultSet();
            return Optional.of(insuranceRowMapper.rowMap(resultSet));
        }
        return null;
    }


    @Override
    @SneakyThrows
    public boolean save(Insurance insurance){
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_INSURED_PERS);
        preparedStatement.setLong(1,insurance.getUser().getId());
        preparedStatement.setString(2,insurance.getTypeInsurance());
        preparedStatement.setDate(3, insurance.getStartDate());
        preparedStatement.setDate(4, insurance.getFinishDate());
        preparedStatement.executeUpdate();
        return true;

    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID_FROM_INS);
        preparedStatement.setLong(1,id);
        preparedStatement.executeUpdate();



    }

    @Override
    @SneakyThrows
    public List<Insurance> findAll() {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Insurance> insurances = new ArrayList<>();
            while (resultSet.next()){
                insurances.add(insuranceRowMapper.rowMap(resultSet));
            }
            return insurances;
        }
        return null;
    }

    @Override
    @SneakyThrows
    public List<Insurance> findAllByUserId(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SEARCH_INSURANCE_BY_USER_ID);
        preparedStatement.setLong(1, id);
        if (preparedStatement.execute()) {
            List<Insurance> insurances = new ArrayList<>();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                insurances.add(insuranceRowMapper.rowMap(resultSet));
                return insurances;
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
}




