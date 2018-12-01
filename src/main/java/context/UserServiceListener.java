package context;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.*;
import services.BalanceService;
import services.BalanceServiceImpl;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class UserServiceListener implements ServletContextListener {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/bank_service";


    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Class.forName("org.postgresql.Driver");
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        BankUserRepository bankUserRepository = new BankUserRepository(dataSource);
        UsersService usersService = new UsersServiceImpl(dataSource);
        CategoryRepository categoryRepository = new CategoryRepository(dataSource);
        TransactionRepository transactionRepository = new TransactionRepository(dataSource);
        BankAccountRepository bankAccountRepository = new BankAccountRepository(dataSource);
        CardRepository cardRepository = new CardRepository(dataSource);
        BalanceService balanceService = new BalanceServiceImpl(dataSource);
        UUIDRepository uuidRepository = new UUIDRepository(dataSource);

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("bankUserRepository", bankUserRepository);
        servletContext.setAttribute("usersService", usersService);
        servletContext.setAttribute("cardRepository" , cardRepository);
        servletContext.setAttribute("bankAccountRepository" , bankAccountRepository);
        servletContext.setAttribute("categoryRep", categoryRepository);
        servletContext.setAttribute("transactionRepository", transactionRepository);
        servletContext.setAttribute("dataSource", dataSource);
        servletContext.setAttribute("balanceService",balanceService);
        servletContext.setAttribute("uuidRepository",uuidRepository);
        List<String> packages = new ArrayList<>();
        packages.add("repositories");
        packages.add("services");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
