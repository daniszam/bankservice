package filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter("/home")
public class ThemaFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String thema = request.getParameter("thema");
        if(request.getCookies() != null) {
            if (thema != null) {
                Cookie cookie = new Cookie("thema", thema);
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
            } else {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("thema")) {
                        thema = cookie.getValue();
                    }
                }
                if (thema == null) {
                    thema = "standart";
                }
            }
        }else{
            Cookie cookie = new Cookie("thema", thema);
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
        }

        request.setAttribute("thema", thema);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
