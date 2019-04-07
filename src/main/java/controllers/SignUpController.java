package controllers;


import forms.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UsersService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public String get(HttpServletRequest request){
        request.getSession().removeAttribute("puted");
        return "forward:/signUpCard";
    }

    @PostMapping
    public String post(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        SignUpForm userForm = SignUpForm.builder()
                .email(email)
                .password(password)
                .birthday(birthday)
                .build();

        if(request.getSession().getAttribute("puted")==null) {
            if (usersService.signUp(userForm)) {
                return "redirect:/signIn";
            }
            request.getSession().setAttribute("puted", "true");
        }
        return "redirect:/sigUp";
    }
}
