package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import models.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import repositories.*;
import utils.Circle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/mySpace")
public class MySpaceServlet extends HttpServlet {
    private CategoryRepository categoryRepository;
    private TransactionRepository transactionRepository;
    private CardRepository cardRepository;
    private BankAccountRepository bankAccountRepository;
    private HttpSession httpSession;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Circle circle;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         httpSession = request.getSession();
         httpSession.setAttribute("category", categoryRepository.findAll());
         User user = (User) httpSession.getAttribute("user");
         List<Card> cards = cardRepository.findAllByUserId(user.getId());
         List<BankAccount> bankAccounts = bankAccountRepository.findAllByUserId(user.getId());
         circle = new Circle();
         int percent = circle.getPercent(cards, bankAccounts);
         httpSession.setAttribute("percent", percent);
         request.getRequestDispatcher("/WEB-INF/JSP/MySpace.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray items = new JSONArray();
        if(req.getParameter("items")!=null) {
             items = new JSONArray(req.getParameter("items"));
        }
        JSONArray types = new JSONArray(req.getParameter("type"));
        List<Transaction> transactions = new ArrayList<>();
        User user = (User)httpSession.getAttribute("user");
        List<Balance> balances = new ArrayList<>();
        int j = 0;
        for(int i = 0; i<items.length(); i++){
            if(types.length()<=i){
                 j=0;
            }
            Transaction transaction = Transaction
                    .builder()
                    .category(Category
                            .builder()
                            .id(items.getJSONObject(i).getLong("id"))
                            .build())
                    .price(items.getJSONObject(i).getFloat("value"))
                    .dateTime(new Date(new java.util.Date().getTime()))
                    .user(user)
                    .build();
            circle.setCountMoney(circle.getCountMoney()-transaction.getPrice());
            transactionRepository.save(transaction);
            for(int k =0; k<user.getBalances().size(); k++){

                Balance balance = user.getBalances().get(k);
                JSONObject jsonObject = types.getJSONObject(j);
                String[] idStr = jsonObject.getString("id").split("\\.");
                Long id = Long.parseLong(idStr[1]);
                if(balance.getClass().getSimpleName()
                        .equals(jsonObject.getString("type")) &&
                        balance.getId().equals(id)){
                    balance.setBalance(balance.getBalance()-transaction.getPrice());
                    transactionRepository.saveToBalance(transaction.getId(), balance);
                    Balance jsonBalance = balance;
                    jsonBalance.setUser(null);
                    jsonBalance.setName(balance.getClass().getSimpleName());
                    balances.add(jsonBalance);
                }
            }
            j++;
            transactions.add(transaction);
        }
        user.getTransactions().addAll(transactions);
        String json = objectMapper.writeValueAsString(balances);
        circle.updatePercent();
        String percent = objectMapper.writeValueAsString(circle);
        String jsons = "["+json+","+percent+"]";
        resp.setContentType("application/json");
        resp.getWriter().write(jsons);


    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        categoryRepository = (CategoryRepository) servletContext.getAttribute("categoryRep");
        transactionRepository = (TransactionRepository) servletContext.getAttribute("transactionRepository");
        bankAccountRepository = (BankAccountRepository) servletContext.getAttribute("bankAccountRepository");
        cardRepository = (CardRepository) servletContext.getAttribute("cardRepository");

    }
}
