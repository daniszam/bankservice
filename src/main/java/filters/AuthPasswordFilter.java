package filters;


import models.User;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/signIn", "/createPassword", "/signUp"})
public class AuthPasswordFilter implements Filter {

    UsersServiceImpl usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext filterContext = filterConfig.getServletContext();
        usersService = (UsersServiceImpl) filterContext.getAttribute("usersService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();

        User user =(User) httpSession.getAttribute("user");
        if(user == null){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }else{
            if(usersService.signIn(user)){
                httpServletResponse.sendRedirect("/home");
                return;
            }
            if(user.getHashPassword() == null){
                httpServletRequest.getRequestDispatcher("/createPassword").forward(httpServletRequest,httpServletResponse);
                return;
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
