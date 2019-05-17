package ru.itis.darZam.controllers;

import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.darZam.models.Balance;
import ru.itis.darZam.models.Credit;
import ru.itis.darZam.models.User;
import ru.itis.darZam.models.UserDetailsImpl;
import ru.itis.darZam.services.BalanceService;
import ru.itis.darZam.services.CreditService;
import ru.itis.darZam.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CreditController {

    @Autowired
    private UserService userService;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private CreditService creditService;

    @GetMapping("addCredit")
    public String get(ModelMap modelMap, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user  = userService.getById(userDetails.getUser().getId());
        modelMap.put("balances", user.getBalances());
        modelMap.put("user", user);
        return "addCredit";
    }

    @PostMapping("addCredit")
    @SneakyThrows
    public String post(@RequestParam("sum") Float sum,
                       @RequestParam("upDate") String upDateStr,
                       @RequestParam("endDate") String endDateStr,
                       @RequestParam("user_balance") Long id){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date upDate = simpleDateFormat.parse(upDateStr);
        Date endDate = simpleDateFormat.parse(endDateStr);
        Balance balance = balanceService.getById(id);
        Credit credit =  Credit.builder().expirationDate(endDate).startDate(upDate).price(sum).balance(balance).build();
        creditService.save(credit);
        return "redirect:/mySpace";
    }

}
