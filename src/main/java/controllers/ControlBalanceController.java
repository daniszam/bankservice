package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import models.User;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import services.UsersService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/controlBalance")
public class ControlBalanceController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET)
    private String get(ModelMap modelMap, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        usersService.getUserBalances(user);
        return "forward:/controlBalances";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @SneakyThrows
    private String post(ModelMap modelMap, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        JSONArray types = new JSONArray(request.getParameter("items"));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(usersService.check(types, user));
        return json;
    }
}
