package context;

import lombok.SneakyThrows;
import repositories.BankUserRepository;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

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
     //   BankUserRepository bankUserRepository = new BankUserRepository();
     //   UsersService usersService = new UsersServiceImpl(bankUserRepository);
        ServletContext servletContext = servletContextEvent.getServletContext();
      //  servletContext.setAttribute("usersService", usersService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
