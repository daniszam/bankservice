package servlets;

import context.Contexts;
import forms.SignUpForm;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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


    private User user;
    private UsersService usersService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
//        usersService = Contexts.primitive().getComponent(UsersService.class);
        usersService =(UsersService) servletConfig.getServletContext().getAttribute("usersService");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
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
        user.setHashPassword(request.getParameter("password"));
        usersService.signUp(user);
        response.sendRedirect("/home");

    }

}
