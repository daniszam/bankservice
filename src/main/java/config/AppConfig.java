package config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.*;
import services.BalanceServiceImpl;
import services.TransactionServiceImpl;
import services.UsersServiceImpl;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application/application.properties")
public class AppConfig {

    private DataSource dataSource;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String userName;

    @Value("${password}")
    private String password;

    @Value("${driverClassName}")
    private String driverClassName;

    @Bean
    public DataSource dataSource(){
        if (dataSource == null) {
            dataSource = new DriverManagerDataSource(url,userName,password);
            ((DriverManagerDataSource) dataSource).setDriverClassName(driverClassName);
        }
        return dataSource;
    }

    @Bean
    public BankAccountRepository bankAccountRepository(){
        return new BankAccountRepository(dataSource());
    }

    @Bean
    public BankUserRepository bankUserRepository(){
        return new BankUserRepository(dataSource());
    }

    @Bean
    public CardRepository cardRepository(){
        return new CardRepository(dataSource());
    }

    @Bean
    public CategoryRepository categoryRepository(){
        return new CategoryRepository(dataSource());
    }

    @Bean
    public IconRepository iconRepository(){
        return new IconRepository(dataSource());
    }

    @Bean
    public TransactionRepository transactionRepository(){
        return new TransactionRepository(dataSource());
    }

    @Bean
    public UUIDRepository uuidRepository(){
        return new UUIDRepository(dataSource());
    }

    @Bean
    public BalanceServiceImpl balanceService(){
        return new BalanceServiceImpl(dataSource());
    }

    @Bean
    public TransactionServiceImpl transactionService(){
        return new TransactionServiceImpl(transactionRepository(), categoryRepository());
    }

    @Bean
    public UsersServiceImpl usersService(){
         UsersServiceImpl usersService = UsersServiceImpl.builder()
                 .bankAccountRepository(bankAccountRepository())
                 .bankUserRepository(bankUserRepository())
                 .cardRepository(cardRepository())
                 .build();
         return usersService;
    }

}
