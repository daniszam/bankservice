package context;

import config.AppConfig;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.*;
import services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class UserServiceListener implements ServletContextListener {

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        //beans from XML beans config
//        BankUserRepository bankUserRepository = context.getBean(BankUserRepository.class);
//        UsersService usersService = context.getBean(UsersServiceImpl.class);
//        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
//        TransactionRepository transactionRepository = context.getBean(TransactionRepository.class);

       // ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //beans from AppConfig
        BankAccountRepository bankAccountRepository = applicationContext.getBean(BankAccountRepository.class);
        CardRepository cardRepository = applicationContext.getBean(CardRepository.class);
        BalanceService balanceService = applicationContext.getBean(BalanceServiceImpl.class);
        UUIDRepository uuidRepository = applicationContext.getBean(UUIDRepository.class);
        TransactionService transactionService = applicationContext.getBean(TransactionServiceImpl.class);

        BankUserRepository bankUserRepository = applicationContext.getBean(BankUserRepository.class);
        UsersService usersService = applicationContext.getBean(UsersServiceImpl.class);
        CategoryRepository categoryRepository = applicationContext.getBean(CategoryRepository.class);
        TransactionRepository transactionRepository = applicationContext.getBean(TransactionRepository.class);

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
