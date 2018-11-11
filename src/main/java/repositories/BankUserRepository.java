package repositories;

import lombok.SneakyThrows;
import models.*;
import models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import javax.sql.DataSource;
import java.sql.*;

import java.sql.Date;
import java.util.*;


public class BankUserRepository implements Repository<User>, UserRepository {

    private JdbcTemplate jdbcTemplate;


    private Map<User, List<Balance>> userWithBalances;
    private Map<User, List<Transaction>> userWithTransaction;


    private Map<Long, User> userWithIdWithMaps;


    private User theOnlyUser;

    //language=SQL
    public static final String SQL_SELECT_ALL_USER= //"SELECT bank_user.id, bank_user.first_name, bank_user.last_name, " +
           // "bank_user.birthday, bank_user.gender, bank_user.phone_number, " +
          //  "acc.balance, tb.name as type_acc, tb.id, acc.code_word, c.type_card, " +
          //  "c.bank_account_id as card_bank_acc_id, ct.validity_period, ct.percent, credit_type.type_credit, credit.expiration_date, " +
         //   "FROM bank_user " +
            "SELECT  *, c.id AS card_id, credit.id AS credit_id, acc.id AS bank_account_id, c.balance AS card_balance, ct.perzent AS card_perzent " +
            "FROM bank_user " +
            "left join bank_account acc on bank_user.id = acc.bank_user_id " +
            "left join type_bank_account tb on tb.name = acc.type_bank_account " +
            "left join card c ON bank_user.id = c.bank_user_id " +
            "left join card_type ct ON c.type_card = ct.type_card " +
            "left join credit ON credit.bank_user_id = bank_user.id " +
            "left join credit_type ON credit.type_credit = credit_type.type_credit " ;
    //language=SQL
    public static final String SQL_SELECT_ALL_INFORMATION_ONLY_USER= "SELECT bank_user.id, bank_user.first_name, bank_user.last_name, " +
            "bank_user.birthday, bank_user.gender, bank_user.phone_number " +
            "FROM bank_user ";
    //language=SQL
    public static final String SQL_INSERT_INTO_USER = "INSERT INTO bank_user (first_name, last_name, phone_number, birthday, gender, hash_password, email) " +
    "VALUES (?, ?, ?, ?, ?, ?, ?) ";

    //language=SQL
    public static final String SQL_DELETE_FROM_USER_BY_ID = "DELETE FROM bank_user " +
            "where bank_user.id=?";

    //language=SQL
    public static final String SQL_SELECT_USER_BY_ID = "SELECT bank_user.id, bank_user.first_name, bank_user.last_name, " +
            "bank_user.birthday, bank_user.gender, bank_user.phone_number, " +
            "acc.balance, tb.name as type_acc, tb.id, acc.code_word, c.type_card, " +
            "c.bank_account_id as card_bank_acc_id, ct.validity_period, ct.perzent " +
            "FROM bank_user " +
            "left join bank_account acc on bank_user.id = acc.bank_user_id " +
            "left join type_bank_account tb on tb.name = acc.type_bank_account " +
            "left join card c ON bank_user.id = c.bank_user_id " +
            "left join card_type ct ON c.type_card = ct.type_card " +
            "left join credit ON credit.bank_user_id = bank_user.id WHERE bank_user.id =?";

    //language=SQL
    public static final String SQL_SELECT_USER_BY_EMAIL ="SELECT DISTINCT ON(c.id) bank_user.id, bank_user.first_name, bank_user.last_name, " +
            "bank_user.birthday, bank_user.gender, bank_user.phone_number, bank_user.email, bank_user.hash_password,  " +
            "acc.balance,acc.id AS bank_account_id,  " +
            " c.id AS card_id, c.balance AS card_balance, c.up_date, t.id AS transaction_id, " +
            " t.transfer, t.category_id, t.date_time " +
            "FROM bank_user " +
            "left join bank_account acc on bank_user.id = acc.bank_user_id " +
            "left join card c ON bank_user.id = c.bank_user_id " +
            "left join credit ON credit.bank_user_id = bank_user.id " +
            "LEFT JOIN  transaction t on bank_user.id = t.user_id " +
            "WHERE bank_user.email =?";



    public BankUserRepository (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    private RowMapper<User> userRowMapper = ((resultSet, i) ->User.builder()
            .id(resultSet.getLong("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .birthday(resultSet.getDate("birthday"))
            //.gender(resultSet.getString("gender").charAt(0))
            .email(resultSet.getString("email"))
            .hashPassword(resultSet.getString("hash_password"))
            .phoneNumber(resultSet.getString("phone_number"))
            .build());

    private RowMapper<User> userWithOrdersForOneUserRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            if (userWithBalances.size() == 0) {
                User newUser = userRowMapper.mapRow(resultSet, i);
                userWithBalances.put(newUser, new ArrayList<>());
                userWithTransaction.put(newUser, new ArrayList<>());
                theOnlyUser = newUser;
            }

            Card card = Card.builder()
                   // .cardType(resultSet.getString("type_card"))
                   // .percent(resultSet.getFloat("card_perzent"))
                    .user(theOnlyUser)
                   // .upDate(resultSet.getDate("up_date"))
                    .id(resultSet.getLong("card_id"))
                    .balance(resultSet.getFloat("card_balance"))
                    .build();

//            Credit credit = Credit.builder()
//                    .type(resultSet.getString("type_credit"))
//                    .expirationDate(resultSet.getUpDate("expiration_date"))
//                    .user(theOnlyUser)
//                    .percent(resultSet.getFloat("percent"))
//                    .id(resultSet.getLong("credit_id"))
//                    .build();

            models.Transaction transaction = models.Transaction.builder()
                    .price(resultSet.getFloat("transfer"))
                    .dateTime(resultSet.getDate("date_time"))
                    .category(Category.builder()
                            .id(resultSet.getLong("category_id"))
                            .build())
                    .id(resultSet.getLong("transaction_id"))
                    .user(theOnlyUser)
                    .build();


            BankAccount bankAccount = BankAccount.builder()
                    .id(resultSet.getLong("bank_account_id"))
                    .bankAccounNumber(resultSet.getLong("bank_account_id"))
                    .balance(resultSet.getFloat("balance"))
                   // .typeBankAccount(resultSet.getString("type_bank_account"))
                    .user(theOnlyUser)
                    .build();


            if(bankAccount.getBankAccounNumber() != 0) {
                if(!userWithBalances.get(theOnlyUser).contains(bankAccount)) {
                    userWithBalances.get(theOnlyUser).add(bankAccount);
                }
            }
            if(transaction.getId()!= 0){
                if(!userWithTransaction.get(theOnlyUser).contains(transaction)) {
                    userWithTransaction.get(theOnlyUser).add(transaction);
                }
            }
//            if(credit.getId() != 0){
//                userWithBalances.get(theOnlyUser).add(credit);
//            }
            if(card.getId() != 0) {
                if(!userWithBalances.get(theOnlyUser).contains(card)) {
                    userWithBalances.get(theOnlyUser).add(card);
                }
            }
            return theOnlyUser;
        }

    };


    private RowMapper<User> userWithOrdersRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            Long currentUserId = resultSet.getLong("id");
            if (!userWithIdWithMaps.containsKey(currentUserId)) {
                User newUser = userRowMapper.mapRow(resultSet, i);
                newUser.setCredits(new ArrayList<>());
                newUser.setBalances(new ArrayList<>());
                newUser.setTransactions(new ArrayList<>());
                userWithIdWithMaps.put(currentUserId, newUser);
            }

            Card card = Card.builder()
                   // .cardType(resultSet.getString("type_card"))
                    //.percent(resultSet.getFloat("card_perzent"))
                    .user(userWithIdWithMaps.get(currentUserId))
                    .id(resultSet.getLong("card_id"))
                    .balance(resultSet.getFloat("card_balance"))
                    .build();

            models.Transaction transaction = models.Transaction.builder()
                    .price(resultSet.getFloat("transfer"))
                    .dateTime(resultSet.getDate("date_time"))
                    .category(Category.builder()
                            .id(resultSet.getLong("category_id"))
                            .build())
                    .user(userWithIdWithMaps.get(currentUserId))
                    .id(resultSet.getLong("transaction_id"))
                    .build();

            Credit credit = Credit.builder()
                    //.type(resultSet.getString("type_credit"))
                    .expirationDate(resultSet.getDate("expiration_date"))
                    .user(userWithIdWithMaps.get(currentUserId))
                    .percent(resultSet.getFloat("percent"))
                    .id(resultSet.getLong("credit_id"))
                    .build();


            BankAccount bankAccount = BankAccount.builder()
                    .id(resultSet.getLong("bank_account_id"))
                    .bankAccounNumber(resultSet.getLong("bank_account_id"))
                    .balance(resultSet.getFloat("balance"))
                   // .typeBankAccount(resultSet.getString("type_bank_account"))
                    .percent(resultSet.getFloat("percent"))
                    .user(userWithIdWithMaps.get(currentUserId))
                    .build();

            User currentUser = userWithIdWithMaps.get(currentUserId);
            List<Balance> balances = currentUser.getBalances();
            List<Transaction> transactions = currentUser.getTransactions();
            List<Credit> credits = currentUser.getCredits();

            if(transaction.getId()!=0){
                transactions.add(transaction);
            }

            if(bankAccount.getBankAccounNumber() !=0) {
                balances.add(bankAccount);
            }
            if(card.getId() != 0) {
                balances.add(card);
            }
            if(credit.getId() !=0) {
                credits.add(credit);
            }
            return currentUser;
        }
    };

    @SneakyThrows
    public Optional<User> findOne(Long id) {
        userWithBalances =  new HashMap<>();
        jdbcTemplate.query(SQL_SELECT_USER_BY_ID,userWithOrdersForOneUserRowMapper, id);
        theOnlyUser.setBalances(userWithBalances.get(theOnlyUser));
        User result = theOnlyUser;
        theOnlyUser = null;
       userWithBalances.clear();
       return Optional.of(result);
    }


    @SneakyThrows
    public boolean save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(SQL_INSERT_INTO_USER, new String[] {"id"});
                    if(user.getFirstName()!=null) {
                        preparedStatement.setString(1, user.getFirstName());
                    }else{
                        preparedStatement.setNull(1, Types.VARCHAR);
                    }
                    if(user.getLastName()!=null) {
                        preparedStatement.setString(2, user.getLastName());
                    }else{
                        preparedStatement.setNull(2, Types.VARCHAR);
                    }

                    if(user.getPhoneNumber()!=null) {
                        preparedStatement.setString(3, user.getPhoneNumber());
                    } else{ preparedStatement.setNull(3, Types.VARCHAR); }
                    if(user.getBirthday() !=null) {
                        preparedStatement.setDate(4, user.getBirthday());
                    } else{preparedStatement.setNull(4, Types.DATE); }

                    if(user.getGender()!=null) {
                        preparedStatement.setShort(5, user.getGender());
                    }else{
                        preparedStatement.setNull(5, Types.SMALLINT);
                    }
                    preparedStatement.setString(6, user.getHashPassword());
                    preparedStatement.setString(7, user.getEmail());
                    return preparedStatement;
                }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return true;
    }


    @SneakyThrows
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_FROM_USER_BY_ID);
    }

    @SneakyThrows
    public List<User> findAll() {
        userWithIdWithMaps = new HashMap<>();
        return jdbcTemplate.query(SQL_SELECT_ALL_USER, userWithOrdersRowMapper);
    }

    @Override
    public Object findByName() {
        return null;
    }

    @SneakyThrows
    public Optional<User> findOneByEmail(String email){
        userWithBalances =  new HashMap<>();
        userWithTransaction = new HashMap<>();
        jdbcTemplate.query(SQL_SELECT_USER_BY_EMAIL,userWithOrdersForOneUserRowMapper, email);


        List<Transaction> transactions =userWithTransaction.get(theOnlyUser);
        List<Balance> balances = userWithBalances.get(theOnlyUser);


        theOnlyUser.setTransactions(transactions);
        theOnlyUser.setBalances(balances);
        User result = theOnlyUser;


        theOnlyUser = null;
        userWithBalances.clear();
        userWithTransaction.clear();
        return Optional.of(result);
    }
}

