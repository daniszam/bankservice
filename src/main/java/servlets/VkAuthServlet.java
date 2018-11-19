package servlets;

import models.User;
import models.VkAuthUser;
import services.UsersService;
import services.UsersServiceImpl;
import services.VkAuth;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/vkAuth")
public class VkAuthServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("code") == null) {
            response.sendRedirect("https://oauth.vk.com/authorize?client_id=6743597&display=page" +
                    "&redirect_uri=http://localhost:8080/vkAuth&scope=email&response_type=code&v=5.87");
        }else{
            String code = request.getParameter("code");
            VkAuth vkAuth = new VkAuth();
            VkAuthUser vkAuthUser = vkAuth.getUserToken(code);
            User user = vkAuth.getUser(vkAuthUser);
            usersService.signIn(user);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", user);
            response.sendRedirect("/createPassword");
            

        }
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");

    }
}
