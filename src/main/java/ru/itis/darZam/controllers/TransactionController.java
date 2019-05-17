package ru.itis.darZam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.darZam.models.Transaction;
import ru.itis.darZam.models.User;
import ru.itis.darZam.models.UserDetailsImpl;
import ru.itis.darZam.models.dto.TransactionDto;
import ru.itis.darZam.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TransactionController {

    @Autowired
    private UserService userService;

    @GetMapping("transaction")
    public String get(ModelMap modelMap, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userService.getById(userDetails.getUser().getId());
        List<TransactionDto> transactionDtos = user.getTransactions().stream().map(TransactionDto::new).collect(Collectors.toList());
        modelMap.put("user", user);
        modelMap.put("transaction", transactionDtos);
        return "transaction";
    }
}
