package filters;


import com.sun.net.httpserver.HttpExchange;
import models.User;
import services.UsersServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/addBalance", "/mySpace","/controlBalance"})
public class AuthContentFilter implements Filter {


    UsersServiceImpl usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext filterContext = filterConfig.getServletContext();
        usersService = (UsersServiceImpl) filterContext.getAttribute("usersService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();

        User user =(User) httpSession.getAttribute("user");
        if(user != null){
            if(user.getHashPassword() == null){
                httpServletRequest.getRequestDispatcher("/createPassword").forward(httpServletRequest,httpServletResponse);
                return;
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            httpServletResponse.sendRedirect("/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}
