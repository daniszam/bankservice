package servlets;

import context.ApplicationDiContext;
import context.Contexts;
import forms.AddBalanceForm;
import models.*;
import repositories.IconRepository;
import services.BalanceService;
import services.BalanceServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/addBalance")
public class AddBalanceServlet extends HttpServlet {
    private List<Balance> balances = new ArrayList<>();
    private BalanceService balanceService;
    private IconRepository iconRepository;
    private List<Icon> icons;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (balances.size() == 0) {
            balances.add(Card.builder().build());
            balances.add(BankAccount.builder().build());
        }
        request.setAttribute("types", balances);
        icons = iconRepository.findAll();
        request.setAttribute("icons", icons);
        request.getRequestDispatcher("/WEB-INF/ftl/addBalance.ftl").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sum = request.getParameter("sum");
        String upSum = request.getParameter("upSum");
        AddBalanceForm addBalanceForm = AddBalanceForm.builder()
                .balance(balances.get(Integer.parseInt(request.getParameter("types"))))
                .date(request.getParameter("upDate"))
                .sum(sum)
                .name(request.getParameter("balance_name"))
                .icon(icons.get(Integer.parseInt(request.getParameter("icon"))))
                .upSum(upSum)
                .user((User) request.getSession().getAttribute("user"))
                .build();

        balanceService.add(addBalanceForm);
        response.sendRedirect("/mySpace");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationDiContext applicationContext = Contexts.primitive();
        iconRepository = applicationContext.getComponent(IconRepository.class);
        balanceService = applicationContext.getComponent(BalanceServiceImpl.class);
    }
}
