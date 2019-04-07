package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/createPassword")
public class AuthByLingController {

    @Autowired
    private UsersService service;
    private User user;

    @RequestMapping(method = RequestMethod.GET)
    private String get(ModelMap modelMap, HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        user = (User) httpSession.getAttribute("user");
        if (user != null) {
            request.setAttribute("email", user.getEmail());
            return "forward:/createPassword";
        }
        return "redirect:/signUp";
    }

    @RequestMapping(method = RequestMethod.POST)
    private String post(ModelMap modelMap, HttpServletRequest request){
        user.setHashPassword(request.getParameter("password"));
        service.signUp(user);
        return "redirect:/home";
    }

}
