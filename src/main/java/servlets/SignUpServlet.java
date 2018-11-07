package servlets;

import lombok.SneakyThrows;
import models.User;
import models.VkAuthUser;
import services.UsersService;
import services.VkAuth;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {


    private UsersService usersService;
    private boolean vk;

    @Override
    @SneakyThrows
    public void init(ServletConfig servletConfig) {
        ServletContext servletContext = servletConfig.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(vk) {
            VkAuth vkAuth = new VkAuth();
            VkAuthUser vkAuthUser = vkAuth.getUserToken(request.getParameter("code"));
            User user = vkAuth.getUser(vkAuthUser);
            System.out.println(request.getParameter("code"));
            System.out.println(user);
            vk=false;
        }
        request.getRequestDispatcher("/WEB-INF/JSP/SignUp.jsp").forward(request, response);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("sex");
        String date = request.getParameter("birthday");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date(simpleDateFormat.parse(date).getTime());
        System.out.println(email + " " + password + " " + firstName + " " + lastName + " " +
                gender + " " + birthday);
        System.out.println(gender);
        UserForm userForm = UserForm.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .birthday(birthday)
                .build();*/
        vk = true;
        response.sendRedirect("https://oauth.vk.com/authorize?client_id=6743597&display=page&redirect_uri=http://localhost:8080&scope=email&response_type=code&v=5.87");
        VkAuth vkAuth = new VkAuth();
       // System.out.println(vkAuth.getUserToken(request));
        //usersService.signUp(userForm);
        //response.sendRedirect("/signIn");
    }
}


