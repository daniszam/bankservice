package controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import models.Balance;
import models.Category;
import models.Transaction;
import models.User;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.TransactionServiceImpl;
import services.UsersService;
import utils.CategoryPercent;
import utils.Circle;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/mySpace")
public class MySpaceController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private TransactionServiceImpl transactionService;
    private Circle circle;

    @GetMapping
    public String get(HttpServletRequest request){
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
        return "forward:/mySpace";
    }

    @PostMapping(produces = "application/json")
    @SneakyThrows
    public String post(HttpServletRequest req) {
        JSONArray items = new JSONArray();
        if (req.getParameter("items") != null) {
            items = new JSONArray(req.getParameter("items"));
        }
        User user = (User) req.getSession().getAttribute("user");

        transactionService.setUser(user);
        List<Balance> balances = transactionService.getUsefulBalances(new JSONArray(req.getParameter("type")));
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

        return jsons;
    }

}
