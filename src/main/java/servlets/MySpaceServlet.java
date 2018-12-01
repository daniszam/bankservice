package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import context.ApplicationDiContext;
import context.Contexts;
import models.*;
import models.Transaction;
import org.json.JSONArray;
import repositories.*;
import services.TransactionService;
import services.TransactionServiceImpl;
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
        request.setAttribute("category", categoryRepository.findAll());
        User user = (User) httpSession.getAttribute("user");
        List<Card> cards = cardRepository.findAllByUserId(user.getId());
        List<BankAccount> bankAccounts = bankAccountRepository.findAllByUserId(user.getId());
        circle = new Circle();


        List<Transaction> transactions = user.getTransactions();
        CategoryPercent categoryPercent = new CategoryPercent();
        Map<Category, Float> categoryPercentMap = categoryPercent.getCategoryUtils(transactions);
        if (transactions.size() > 0) {
            int random = (int) (transactions.size() * Math.random());
            request.setAttribute("randomCategory", transactions.get(random).getCategory().getName());
            int randPercent = (categoryPercentMap.get(transactions.get(random).getCategory())).intValue();
            request.setAttribute("randomPercent", randPercent);
        }
        int percent = circle.getPercent(cards, bankAccounts);
        httpSession.setAttribute("percent", percent);
        request.setAttribute("percentMap", categoryPercentMap);
        request.getRequestDispatcher("/WEB-INF/JSP/MySpace.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray items = new JSONArray();
        if (req.getParameter("items") != null) {
            items = new JSONArray(req.getParameter("items"));
        }
        JSONArray types = new JSONArray(req.getParameter("type"));
        User user = (User) httpSession.getAttribute("user");

        TransactionService transactionService = new TransactionServiceImpl(user, transactionRepository);
        transactionService.getUsefulBalances(types);
        List<Transaction> transactions = transactionService.getTransactions(items);
        List<Balance> balances = ((TransactionServiceImpl) transactionService).getBalances();
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(balances.get(0).getBalance());
            circle.setCountMoney(circle.getCountMoney() - transactions.get(i).getPrice());
        }
        user.getTransactions().addAll(transactions);
        String json = objectMapper.writeValueAsString(balances);
        circle.updatePercent();
        CategoryPercent categoryPercent = new CategoryPercent();
        String categoryPercentMap = objectMapper.writeValueAsString(categoryPercent
                .getCategoryUtilsList(user.getTransactions()));
        String percent = objectMapper.writeValueAsString(circle);
        String jsons = "[" + json + "," + percent + "," + categoryPercentMap + "]";
        resp.setContentType("application/json");
        resp.getWriter().write(jsons);


    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationDiContext applicationContext = Contexts.primitive();
        categoryRepository = applicationContext.getComponent(CategoryRepository.class);
        transactionRepository = applicationContext.getComponent(TransactionRepository.class);
        bankAccountRepository = applicationContext.getComponent(BankAccountRepository.class);
        cardRepository = applicationContext.getComponent(CardRepository.class);

    }
}
