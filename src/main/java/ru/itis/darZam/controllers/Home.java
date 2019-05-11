package ru.itis.darZam.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.darZam.models.UserDetailsImpl;

@Controller
public class Home {

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetailsImpl userDetails,
                       ModelMap modelMap){
        modelMap.put("user", userDetails!=null? userDetails.getUser() : null);
        return "home";
    }
}
