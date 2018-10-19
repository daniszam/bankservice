package repositories;

import lombok.SneakyThrows;
import mappers.RowMapper;
import models.BankAccount;
import models.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements Repository<Transaction>, TransactionRep<BankAccount> {


    private Connection connection;

    //language=SQL
    public static final String SQL_SEARCH_ACCOUNT_BALANCE = "SELECT balance FROM bank_account WHERE id=?";

    //language=SQL
    public static final String SQL_TRANSFER_FROM_ACCOUNT = "UPDATE bank_account SET balance=balance - ? WHERE id=?";

    //language=SQL
    public static final String SQL_TRANSFER_TO_ACCOUNT = "UPDATE bank_account SET balance=balance + ? WHERE id=?";

    //language=SQL
    public static final String SQL_INSERT_TRANSACTION = "INSERT INTO transactions (date_time, from_account, to_account, transfer) " +
            "VALUES (?,?,?,?) " ;


    //language=SQL
    public static final String SQL_DELETE_TRANSACTION = "DELETE FROM transactions Where id = ?";

    //language=SQL
    public static final String SQL_ALL_TRANSACTION = "SELECT * FROM transactions";

    //language=SQL
    public static final String SQL_DELETE_BY_ACCOUNT_ID = "DELETE FROM transactions WHERE from_account=? OR to_account=?";




    public TransactionRepository (Connection connection){
        this.connection = connection;
    }


    private RowMapper<Transaction> transactionRowMapper = new RowMapper<Transaction>() {
        @Override
        @SneakyThrows
        public Transaction rowMap(ResultSet resultSet) {
            BankAccountRepository bankAccountRepository = new BankAccountRepository(connection);
            Optional<BankAccount> fromBankAcoount = bankAccountRepository.findOne(resultSet.getLong("from_account"));
            Optional<BankAccount> toBankAccount = bankAccountRepository.findOne(resultSet.getLong("to_account"));
            Transaction transaction = Transaction.builder()
                    .dateTime(resultSet.getDate("date_time"))
                    .fromAccount(fromBankAcoount.get())
                    .toAccount(toBankAccount.get())
                    .transfer(resultSet.getDouble("transfer"))
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
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SEARCH_ACCOUNT_BALANCE);
        preparedStatement.setLong(1,transaction.getFromAccount().getBankAccounNumber());
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            Double balance = resultSet.getDouble(1);
            if(balance < transaction.getTransfer() ){
                System.out.println("not money");
                return false;
            } else {
                preparedStatement = connection.prepareStatement(SQL_TRANSFER_FROM_ACCOUNT);
                preparedStatement.setDouble(1,transaction.getTransfer());
                preparedStatement.setLong(2,transaction.getFromAccount().getBankAccounNumber());
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(SQL_TRANSFER_TO_ACCOUNT);
                preparedStatement.setDouble(1,transaction.getTransfer());
                preparedStatement.setLong(2,transaction.getToAccount().getBankAccounNumber());
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(SQL_INSERT_TRANSACTION);
                preparedStatement.setDate(1, transaction.getDateTime());
                preparedStatement.setLong(2, transaction.getFromAccount().getBankAccounNumber());
                preparedStatement.setLong(3,transaction.getToAccount().getBankAccounNumber());
                preparedStatement.setDouble(4,transaction.getTransfer());
                preparedStatement.executeUpdate();
                return true;
            }

        }
        return false;
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TRANSACTION);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }
    @SneakyThrows
    public void deleteByAccount(Long id){
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ACCOUNT_ID);
        preparedStatement.setLong(1,id);
        preparedStatement.setLong(2,id);
        preparedStatement.executeUpdate();
    }

    @Override
    @SneakyThrows
    public List<Transaction> findAll() {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALL_TRANSACTION);
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Transaction> transactions = new ArrayList<>();
            while(resultSet.next()){
                transactions.add(transactionRowMapper.rowMap(resultSet));
            }
            return transactions;
        }
        return null;
    }


    @Override
    public List<BankAccount> searchByAccount(BankAccount bankAccount) {
        return null;
    }
}
