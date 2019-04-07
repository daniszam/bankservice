package controllers;

import forms.AddBalanceForm;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.BalanceService;
import services.IconService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/addBalance")
public class AddBalanceController {

    @Autowired
    private IconService iconService;
    @Autowired
    private BalanceService balanceService;

    private List<Balance> balances = new ArrayList<>();
    private List<Icon> icons;


    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap modelMap){
        if (balances.size() == 0) {
            balances.add(Card.builder().build());
            balances.add(BankAccount.builder().build());
        }
        icons = iconService.getIcons();
        modelMap.put("types", balances);
        modelMap.put("icons", icons);
        return "addBalance";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String post(ModelMap modelMap, HttpServletRequest request){
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

        return "redirect:/mySpace";
    }
}
