package servlets;

import context.Contexts;
import forms.SignUpForm;
import models.User;
import services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/createPassword")
public class AuthByLinkServlet extends HttpServlet {

    private HttpSession httpSession;
    private User user;
    private UsersService usersService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        usersService = Contexts.primitive().getComponent(UsersService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        httpSession = request.getSession();
        user = (User) httpSession.getAttribute("user");
        if (user != null) {
            request.setAttribute("email", user.getEmail());
            request.getRequestDispatcher("/WEB-INF/ftl/createPassword.ftl").forward(request, response);
        } else {
            response.sendRedirect("/signUp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignUpForm signUpForm = SignUpForm.builder()
                .password(request.getParameter("password"))
                .email(user.getEmail())
                .birthday(user.getBirthday().toString())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .build();
        user.setHashPassword(request.getParameter("password"));
        usersService.signUp(user);
        response.sendRedirect("/home");

    }

}
