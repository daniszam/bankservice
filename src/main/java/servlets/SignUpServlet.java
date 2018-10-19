package servlets;

import forms.UserForm;
import lombok.SneakyThrows;
import models.User;
import repositories.BankUserRepository;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {


    private UsersService usersService;

    @Override
    @SneakyThrows
    public void init(ServletConfig servletConfig) {
        ServletContext servletContext = servletConfig.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/JSP/SignUp.jsp").forward(request, response);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("sex");
        String date = request.getParameter("birthday");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date(simpleDateFormat.parse(date).getTime());
        System.out.println(email + " " + password + " " + firstName + " " + lastName + " " +
                gender + " " + birthday);
        System.out.println(gender);
        UserForm userForm = UserForm.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .birthday(birthday)
                .build();

        usersService.signUp(userForm);
        response.sendRedirect("/signIn");
    }
}


