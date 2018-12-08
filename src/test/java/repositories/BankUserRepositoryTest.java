package repositories;

import models.Category;
import models.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import utils.CategoryPercent;

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
    @Ignore
    public void findOneByEmail() {
      User user =  bankUserRepository.findOneByEmail("danis.zamaliev2011@yandex.ru").get();
        CategoryPercent categoryPercent = new CategoryPercent();
        List<Category> categories = categoryPercent.getCategoryUtils(user.getTransactions());
        System.out.println(categories);

    }
}