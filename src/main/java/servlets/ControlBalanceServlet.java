package servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import context.ApplicationDiContext;
import context.Contexts;
import models.Balance;
import models.User;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/controlBalance")
public class ControlBalanceServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        usersService.getUserBalances(user);
        request.getRequestDispatcher("WEB-INF/ftl/controlBalances.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        JSONArray types = new JSONArray(request.getParameter("items"));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(usersService.check(types, user));
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
//        ApplicationDiContext applicationContext = Contexts.primitive();
//
//        usersService = applicationContext.getComponent(UsersServiceImpl.class);
        usersService =(UsersService) config.getServletContext().getAttribute("usersService");
    }
}
