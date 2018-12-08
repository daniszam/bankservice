package context;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.*;
import services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class UserServiceListener implements ServletContextListener {

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        BankUserRepository bankUserRepository = context.getBean(BankUserRepository.class);
        UsersService usersService = context.getBean(UsersServiceImpl.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        TransactionRepository transactionRepository = context.getBean(TransactionRepository.class);
        BankAccountRepository bankAccountRepository = context.getBean(BankAccountRepository.class);
        CardRepository cardRepository = context.getBean(CardRepository.class);
        BalanceService balanceService = context.getBean(BalanceServiceImpl.class);
        UUIDRepository uuidRepository = context.getBean(UUIDRepository.class);
        TransactionService transactionService = context.getBean(TransactionServiceImpl.class);

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("bankUserRepository", bankUserRepository);
        servletContext.setAttribute("usersService", usersService);
        servletContext.setAttribute("cardRepository" , cardRepository);
        servletContext.setAttribute("bankAccountRepository" , bankAccountRepository);
        servletContext.setAttribute("categoryRepository", categoryRepository);
        servletContext.setAttribute("transactionRepository", transactionRepository);
        servletContext.setAttribute("balanceService",balanceService);
        servletContext.setAttribute("uuidRepository",uuidRepository);
        servletContext.setAttribute("transactionService", transactionService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
