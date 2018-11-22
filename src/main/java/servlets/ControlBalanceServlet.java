package servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import models.Balance;
import models.User;
import org.json.JSONArray;
import org.json.JSONObject;
import services.BalanceService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/controlBalance")
public class ControlBalanceServlet extends HttpServlet {

    private BalanceService balanceService;
    private ObjectMapper objectMapper;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/JSP/controlBalances.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Balance> balances = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("user");
        JSONArray types = new JSONArray(request.getParameter("items"));
        for (int i = 0; i < types.length(); i++) {
            JSONObject type = types.getJSONObject(i);
            Balance balance = user.getBalances().get(type.getInt("id"));
            balance.setBalance(balance.getBalance() + type.getInt("sum"));
            balanceService.increaseBalance(balance);
            balance.setUser(null);
            if (balance.getName() == null) {
                balance.setName(balance.getClass().getSimpleName());
            }
            balances.add(balance);
        }
        String json = objectMapper.writeValueAsString(balances);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        balanceService = (BalanceService) servletContext.getAttribute("balanceService");
        objectMapper = new ObjectMapper();
    }
}
