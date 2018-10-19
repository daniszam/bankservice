package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.BankAccount;
import models.Card;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankAccountRepository implements Repository<BankAccount>, AllByIDRepository<BankAccount> {

    private Connection connection;

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

    public BankAccountRepository(Connection connection){
        this.connection = connection;

    }

    private RowMapper<BankAccount> bankAccountRowMapper = new RowMapper<BankAccount>() {
        @Override
        @SneakyThrows
        public BankAccount rowMap(ResultSet resultSet) {
            BankUserRepository bankUserRepository = new BankUserRepository(connection);
            Optional<User> user = bankUserRepository.findOne(resultSet.getLong("bank_user_id"));
            BankAccount bankAccount = BankAccount.builder()
                    .bankAccounNumber(resultSet.getLong("id"))
                    .balance(resultSet.getFloat("balance"))
                    .typeBankAccount(resultSet.getString("type_bank_account"))
                    .codeWord(resultSet.getString("code_word"))
                    .percent(resultSet.getFloat("percent"))
                    .user(user.get())
                    .build();
            return bankAccount;
        }
    };

    @Override
    @SneakyThrows
    public Optional<BankAccount> findOne(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
        preparedStatement.setLong(1,id);
        if(preparedStatement.execute()){
            BankAccount bankAccount = bankAccountRowMapper.rowMap(preparedStatement.getResultSet());
            return Optional.of(bankAccount);
        }
        return null;
    }

    @Override
    @SneakyThrows
    public boolean save(BankAccount bankAccount) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_BANK_ACCOUNT);
        preparedStatement.setDouble(1, bankAccount.getBalance());
        preparedStatement.setLong(2,bankAccount.getUser().getId());
        preparedStatement.setString(3, bankAccount.getTypeBankAccount());
        preparedStatement.setString(4, bankAccount.getCodeWord());
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BANK_ACCOUNT_BY_ID);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    @SneakyThrows
    public List<BankAccount> findAll(){
    PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_BANK_ACCOUNT);
    List<BankAccount> bankAccounts =  this.getBankAccount(preparedStatement);
    return bankAccounts;
    }

    @SneakyThrows
    private List<BankAccount> getBankAccount(PreparedStatement preparedStatement){
        if(preparedStatement.execute()){
            List<BankAccount> bankAccounts = new ArrayList<>();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                bankAccounts.add(bankAccountRowMapper.rowMap(resultSet));
            }
            return bankAccounts;
        }
        return null;
    }



    @SneakyThrows
    public List<BankAccount> findAllByUserId(Long id){
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BANK_ACC_BY_USER_ID);
        preparedStatement.setLong(1,id);
        List<BankAccount> bankAccounts =  this.getBankAccount(preparedStatement);
        return bankAccounts;
    }

    @SneakyThrows
    @Override
    public void deleteAllByUserId(Long id) {
        List<BankAccount> bankAccounts = this.findAllByUserId(id);
        TransactionRepository transactionRepository = new TransactionRepository(this.connection);
        for (int i=0; i<bankAccounts.size(); i++){
            transactionRepository.deleteByAccount(bankAccounts.get(i).getBankAccounNumber());
        }
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ALL_BY_USER_ID);
        preparedStatement.setLong(1,id);
        preparedStatement.executeUpdate();
    }


    @SneakyThrows
    public List<String> getType(){
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TYPE_BANK_ACCOUNT);
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            List<String> items = new ArrayList<>();
            while(resultSet.next()){
                items.add(resultSet.getString("name"));
            }
            return items;
        }
        return null;
    }
}
