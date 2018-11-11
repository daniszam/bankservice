package repositories;

import models.Card;
import models.Category;
import models.Transaction;
import models.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionRepositoryTest {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/bank_service";

    TransactionRepository transactionRepository;

    @Before
    public void setUp() throws Exception {
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        transactionRepository = new TransactionRepository(dataSource);
    }

    @Test
    public void findOne() {
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteByAccount() {
    }

    @Test
    public void findAll() {
    }

    @Test
    @Ignore
    public void saveTransactions() {
        Transaction transaction = Transaction.builder()
                .category(Category.builder().id(2L).build())
                .dateTime(new Date(new java.util.Date().getTime()))
                .price(12414F)
                .user(User.builder().id(23L).build())
                .build();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
    }

    @Test
    @Ignore
    public void saveToBalance(){
        transactionRepository.saveToBalance(103L, Card.builder().id(2L).build());
    }
}