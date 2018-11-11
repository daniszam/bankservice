package servlets;

import forms.LoginForm;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {


    private UsersService usersService;

    @SneakyThrows
    public void init(ServletConfig servletConfig)  {
        ServletContext servletContext = servletConfig.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/JSP/SignInCard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginForm loginForm = LoginForm.builder()
                .email(email)
                .password(password)
                .build();

        Optional<User> optionalUser = usersService.signIn(loginForm);
        if(optionalUser.isPresent()){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", optionalUser.get());
            response.sendRedirect("/createPassword");
        }
    }
}
