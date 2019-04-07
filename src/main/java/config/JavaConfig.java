package config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import repositories.*;
import services.BalanceServiceImpl;
import services.TransactionServiceImpl;
import services.UsersServiceImpl;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value = {"config", "filters", "repositories", "services", "utils", "servlets", "controllers"})
@PropertySource("classpath:application/application.properties")
public class JavaConfig {

    @Autowired
    private Environment environment;


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/ftl/");
        viewResolver.setSuffix(".ftl");
        return viewResolver;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setPassword(environment.getProperty("db.password", "postgres"));
        dataSource.setUsername(environment.getProperty("db.username", "postgres"));
        dataSource.setUrl(environment.getProperty("db.url", "jdbc:postgresql://localhost:5432/bank_service"));
        dataSource.setDriverClassName(environment.getProperty("db.driverClassName", "org.postgresql.Driver"));
        return dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
