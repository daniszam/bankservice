package app;


import forms.LoginForm;
import lombok.SneakyThrows;
import models.User;
import repositories.*;
import services.UsersService;
import services.UsersServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Demo {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/bank_service";

    @SneakyThrows
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        BankUserRepository bankUserRepository = new BankUserRepository(connection);
        List<User> users = bankUserRepository.findAll();

        LoginForm loginForm = LoginForm.builder()
                .email("ivanivanov@mail.ru")
                .password("ivanisbsvanov")
                .build();
        UsersService usersService = new UsersServiceImpl(bankUserRepository);
        usersService.signIn(loginForm);

        //System.out.println(users);


      //  StartMenu startMenu = new StartMenu(connection);



    }
}
