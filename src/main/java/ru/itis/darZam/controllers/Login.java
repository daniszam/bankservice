package ru.itis.darZam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.darZam.models.User;
import ru.itis.darZam.models.validators.UserValidator;
import ru.itis.darZam.services.UserService;

import javax.validation.Valid;

@Controller
public class Login {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    private String getLogin(ModelMap map){
        return "signInCard";
    }


    @GetMapping("/signUp")
    private String getSignUp(ModelMap map){
        map.put("user", new User());
        return "signUpCard";
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @PostMapping("/signUp")
    private String postSignUp( RedirectAttributes redirectAttributes,
                               @Valid @ModelAttribute("user") User user,
                               BindingResult result){
        if(result.hasErrors()){
            return "signUpCard";
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return "redirect:/signIn";
        }
    }
}

