package servlets;

import forms.LoginForm;
import lombok.SneakyThrows;
import models.UUIDUser;
import models.User;
import repositories.BankUserRepository;
import repositories.UUIDRepository;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.UUID;


@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {


    private UsersService usersService;
    private UUIDRepository uuidRepository;

    @SneakyThrows
    public void init(ServletConfig servletConfig)  {
        ServletContext servletContext = servletConfig.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
        uuidRepository =(UUIDRepository)servletContext.getAttribute("uuidRepository");
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
            if (optionalUser.isPresent()) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", optionalUser.get());
                if (request.getParameter("save_me")!=null) {
                    String cookieValue = UUID.randomUUID().toString();
                    Cookie userKey = new Cookie("remember", cookieValue);
                    userKey.setMaxAge(60 * 60 * 3000);
                    uuidRepository.save(UUIDUser.builder().user(optionalUser.get()).uuid(cookieValue).build());
                    response.addCookie(userKey);
                }
                response.sendRedirect("/home");
            }else {
                response.sendRedirect("/signIn");
            }

        }

}
