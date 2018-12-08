package filters;


import models.UUIDUser;
import models.User;
import repositories.BankUserRepository;
import repositories.UUIDRepository;
import services.UsersService;
import services.UsersServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Optional;


@WebFilter("*")
public class RememberFilter implements Filter {
    private UUIDRepository uuidRepository;
    private BankUserRepository bankUserRepository;
    private UsersService usersService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        uuidRepository = (UUIDRepository) servletContext.getAttribute("uuidRepository");
        bankUserRepository = (BankUserRepository) servletContext.getAttribute("bankUserRepository");
        usersService = (UsersServiceImpl)servletContext.getAttribute("userServiceImpl");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpServletRequest.getCookies() == null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            String userKey;
            if (httpServletRequest.getCookies().length > 0) {
                for (Cookie cookie : httpServletRequest.getCookies()) {
                    if (cookie.getName().equals("remember")) {
                        userKey = cookie.getValue();
                        Optional<UUIDUser> uuidUserOptional = uuidRepository.getOneByUUID(userKey);
                        if (uuidUserOptional.isPresent()) {
                            Long id = uuidUserOptional.get().getUser().getId();
                            user = bankUserRepository.findOne(id).get();
                            usersService.signIn(user);
                            httpSession.setAttribute("user", user);
                        } else {
                            //httpServletResponse.sendRedirect("/signIn");
                        }

                    }

                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
