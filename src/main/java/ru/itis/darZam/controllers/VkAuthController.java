package ru.itis.darZam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.darZam.models.User;
import ru.itis.darZam.models.UserDetailsImpl;
import ru.itis.darZam.models.VkAuthUser;
import ru.itis.darZam.services.UserService;
import ru.itis.darZam.utils.VkAuth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class VkAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/vkAuth")
    public String get(HttpServletRequest request,
                      @RequestParam(value = "code", required = false) String code) {
        if (request.getParameter("code") == null) {
            return "redirect:" + VkAuth.getOauthUri();
        } else {
            VkAuthUser vkAuthUser = VkAuth.getUserToken(code);
            User user = VkAuth.getUser(vkAuthUser);
            Optional<User> optionalUser = userService.getByEmail(user.getEmail());
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                VkAuth.auth(user);
                return "redirect:/home";
            }
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", user);
            return "redirect:/createPassword";
        }
    }


    @GetMapping("/createPassword")
    private String createPassword(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            request.setAttribute("email", user.getEmail());
            return "createPassword";
        }
        return "redirect:/signUp";
    }

    @PostMapping("/createPassword")
    private String post(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        user.setPassword(request.getParameter("password"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/signIn";
    }
}
