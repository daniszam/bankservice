package ru.itis.darZam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.darZam.models.*;
import ru.itis.darZam.models.dto.BalanceUpdateDto;
import ru.itis.darZam.services.BalanceService;
import ru.itis.darZam.services.IconService;
import ru.itis.darZam.services.UserService;

import java.sql.Date;
import java.util.List;


@Controller
public class BalanceController {

    @Autowired
    private IconService iconService;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private UserService userService;

    @GetMapping("/addBalance")
    public String getAddBalance(ModelMap map, @AuthenticationPrincipal UserDetailsImpl userDetails){
        map.put("user", userService.getById(userDetails.getUser().getId()));
        map.put("balanceTypes", balanceService.getBalanceTypes());
        map.put("icons", iconService.getIcons());
        return "addBalance";
    }

    @PostMapping("/addBalance")
    public String postAddBalance(@RequestParam("types") Balance balance,
                                 @RequestParam("sum") Float sum,
                                 @RequestParam(value = "upSum", required = false) Float upSum,
                                 @RequestParam("balance_name") String name,
                                 @RequestParam(value = "upDate", required = false) Date upDate,
                                 @RequestParam("icon") Icon icon,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (balance !=null) {
            balance.setIcon(icon);
            balance.setCount(sum);
            balance.setName(name);
            balance.setUpSum(upSum);
            balance.setUpDate(upDate);
            balance.setUser(userService.getById(userDetails.getUser().getId()));
            balanceService.save(balance);
            return "redirect:/mySpace";
        }else{
            return "redirect:/addBalance";
        }
    }

    @GetMapping("/controlBalance")
    private String controlBalance(ModelMap modelMap, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userService.getById(userDetails.getUser().getId());
        modelMap.put("user", user);
        modelMap.put("balances", user.getBalances());
        return "controlBalances";
    }


    @PostMapping(value = "/controlBalance" ,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody List<BalanceUpdateDto> updateBalance (ModelMap modelMap,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                @RequestBody List<BalanceUpdateDto> balancesToUpdate){
        List<BalanceUpdateDto> updatableBalances = balanceService.updateBalancesById(balancesToUpdate);
        return updatableBalances;
    }
}

