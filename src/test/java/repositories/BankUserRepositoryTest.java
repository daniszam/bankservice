package repositories;

import models.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.Assert.*;

public class BankUserRepositoryTest {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/bank_service";

    BankUserRepository bankUserRepository;

    @Before
    public void setUp() throws Exception {

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        bankUserRepository = new BankUserRepository(dataSource);
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
    @Ignore
    public void findAll() {
        List<User> list = bankUserRepository.findAll();
        System.out.println(bankUserRepository.findAll());
    }

    @Test
    public void findByName() {
    }

    @Test
    public void findOneByEmail() {
        bankUserRepository.findOneByEmail("dsssgs");
    }
}