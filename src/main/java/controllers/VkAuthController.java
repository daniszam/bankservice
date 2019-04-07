package controllers;

import models.User;
import models.VkAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UsersService;
import services.VkAuth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("vkAuth")
public class VkAuthController {

    @Autowired
    private UsersService usersService;


    @GetMapping
    public String get(HttpServletRequest request){
        if (request.getParameter("code") == null) {
            return "redirect:/https://oauth.vk.com/authorize?client_id=6743597&display=page\" +\n" +
                    "                    \"&redirect_uri=http://localhost:8080/vkAuth&scope=email&response_type=code&v=5.87";
        } else {
            String code = request.getParameter("code");
            VkAuth vkAuth = new VkAuth();
            VkAuthUser vkAuthUser = vkAuth.getUserToken(code);
            User user = vkAuth.getUser(vkAuthUser);
            Optional<User> optionalUser = usersService.signIn(user);
            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", user);
            return "redirect:/createPassword";
        }

    }
}
