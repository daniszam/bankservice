package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/logout")
public class LogOutController {

    @GetMapping
    public String get(HttpServletRequest req, HttpServletResponse resp){

        req.getSession().removeAttribute("user");
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("remember")) {
                Cookie remember = cookie;
                remember.setValue(null);
                remember.setMaxAge(0);
                resp.addCookie(remember);
            }
        }
        return "forward:/home";
    }
}
