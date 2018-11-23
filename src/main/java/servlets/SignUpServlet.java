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
        request.getSession().removeAttribute("puted");
        request.getRequestDispatcher("/WEB-INF/JSP/SignUpCard.jsp").forward(request, response);

    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                response.sendRedirect("/signIn");
            } else {
                PrintWriter printWriter = response.getWriter();
                printWriter.println("<script type=\"text/javascript\">");
                printWriter.println("alert('User or password incorrect');");
                printWriter.println("location='/signUp';");
                printWriter.println("</script>");
            }
            request.getSession().setAttribute("puted", "true");
        }

    }


}


