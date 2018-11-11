package repositories;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.Assert.*;

public class CardRepositoryTest {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/bank_service";

    CardRepository cardRepository;

    @Before
    public void setUp() throws Exception {

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        cardRepository = new CardRepository(dataSource);

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
        System.out.println(cardRepository.findAll());
    }

    @Test
    public void findAllByUserId() {
    }

    @Test
    public void deleteAllByUserId() {
    }
}