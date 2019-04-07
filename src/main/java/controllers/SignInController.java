package controllers;

import forms.LoginForm;
import models.UUIDUser;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public String get(){
        return "forward:/signInCard";
    }

    @PostMapping
    public String post(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginForm loginForm = LoginForm.builder()
                .email(email)
                .password(password)
                .build();

        Optional<User> optionalUser = usersService.signIn(loginForm);
        if (optionalUser.isPresent()) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", optionalUser.get());
            if (request.getParameter("save_me") != null) {
                String cookieValue = UUID.randomUUID().toString();
                Cookie userKey = new Cookie("remember", cookieValue);
                userKey.setMaxAge(60 * 60 * 3000);
                usersService.saveUUid(UUIDUser.builder().user(optionalUser.get()).uuid(cookieValue).build());
                response.addCookie(userKey);
            }
            response.setStatus(HttpServletResponse.SC_OK);
            return "redirect:/home";
        }
        return "redirect:/signIn";
    }
}
