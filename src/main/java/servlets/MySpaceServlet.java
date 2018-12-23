package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import context.ApplicationDiContext;
import context.Contexts;
import lombok.SneakyThrows;
import models.*;
import models.Transaction;
import org.json.JSONArray;
import repositories.*;
import services.TransactionService;
import services.TransactionServiceImpl;
import services.UsersService;
import services.UsersServiceImpl;
import utils.CategoryPercent;
import utils.Circle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/mySpace")
public class MySpaceServlet extends HttpServlet {
    private TransactionServiceImpl transactionService;
    private UsersService usersService;
    private Circle circle;

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        usersService.getUserBalances(user);
        List<Transaction> transactions = usersService.getUserTransaction(user);
        List<Category> categories = transactionService.getPercentCategory(transactions);

        circle = new Circle();
        int percent = circle.getPercent(usersService.getUserCard(user), usersService.getBankAccount(user));

        if (transactions.size() > 0) {
            Category category = CategoryPercent.getRandomPercent(categories, transactions);
            request.setAttribute("randomCategory", category);
            request.setAttribute("randomPercent", category.getPercent().intValue());
        }
        request.setAttribute("category", transactionService.getCategorys());
        request.setAttribute("percent", percent);
        request.setAttribute("categories",categories);
        request.getRequestDispatcher("/WEB-INF/ftl/mySpace.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray items = new JSONArray();
        if (req.getParameter("items") != null) {
            items = new JSONArray(req.getParameter("items"));
        }
        User user = (User) req.getSession().getAttribute("user");

        transactionService.setUser(user);
        List<Balance> balances =  transactionService.getUsefulBalances(new JSONArray(req.getParameter("type")));
        List<Transaction> transactions = transactionService.getTransactions(items);
        for (int i = 0; i < transactions.size(); i++) {
            circle.setCountMoney(circle.getCountMoney() - transactions.get(i).getPrice());
        }
        user.getTransactions().addAll(transactions);
        circle.updatePercent();
        //response
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(balances);
        String categoryPercentMap = objectMapper.writeValueAsString(transactionService.getPercentCategory(user.getTransactions()));
        String percent = objectMapper.writeValueAsString(circle);
        String jsons = "[" + json + "," + percent + "," + categoryPercentMap + "]";
        resp.setContentType("application/json");
        resp.getWriter().write(jsons);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationDiContext applicationContext = Contexts.primitive();
        usersService = applicationContext.getComponent(UsersServiceImpl.class);
        transactionService = (TransactionServiceImpl) config.getServletContext().getAttribute("transactionService");

    }
}
