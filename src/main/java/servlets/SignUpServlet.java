package servlets;

import forms.SignUpForm;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
            String code = request.getParameter("code");
            if(code != null) {
                VkAuth vkAuth = new VkAuth();
                VkAuthUser vkAuthUser = vkAuth.getUserToken(code);
                User user = vkAuth.getUser(vkAuthUser);
                System.out.println(request.getParameter("code"));
                usersService.signIn(user);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", user);
                response.sendRedirect("/createPassword");
                //request.getRequestDispatcher("/createPassword").forward(request,response);
                vk = false;
            }else{
                vk = false;
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/JSP/SignUpCard.jsp").forward(request, response);
        }
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("vkauth") !=null) {
            vk = true;
            response.sendRedirect("https://oauth.vk.com/authorize?client_id=6743597&display=page" +
                    "&redirect_uri=http://localhost:8080&scope=email&response_type=code&v=5.87");
        }else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            ///String firstName = request.getParameter("firstName");
           // String lastName = request.getParameter("lastName");
            //String gender = request.getParameter("sex");
            String upDate = request.getParameter("birthday");
            Date birthday = null;
            if(upDate!=null && upDate.length()>8) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                birthday = new Date(simpleDateFormat.parse(upDate).getTime());
            }
            SignUpForm userForm = SignUpForm.builder()
                    .email(email)
                    .password(password)
                    //.firstName(firstName)
                   // .lastName(lastName)
                   // .gender(gender)
                    .birthday(birthday)
                    .build();
            if(usersService.signUp(userForm)){
                response.sendRedirect("/signIn");
            } else {
                PrintWriter printWriter = response.getWriter();
                printWriter.println("<script type=\"text/javascript\">");
                printWriter.println("alert('User or password incorrect');");
                printWriter.println("location='/signUp';");
                printWriter.println("</script>");
            }
            }

        }




}


