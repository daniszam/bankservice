package repositories;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.Assert.*;

public class BankAccountRepositoryTest {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/bank_service";

    BankAccountRepository bankAccountRepository;

    @Before
    public void setUp() throws Exception {
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        bankAccountRepository = new BankAccountRepository(dataSource);
    }

    @Test
    @Ignore
    public void findOne() {
      //  System.out.println(bankAccountRepository.findOne(6L));
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
        System.out.println(bankAccountRepository.findAll());
    }

    @Test
    @Ignore
    public void findAllByUserId() {
        System.out.println(bankAccountRepository.findAllByUserId(6L));
    }

    @Test
    @Ignore
    public void deleteAllByUserId() {
        bankAccountRepository.deleteAllByUserId(8L);
    }

    @Test
    public void getType() {
    }
}