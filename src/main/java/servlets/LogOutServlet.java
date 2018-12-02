package servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("remember")) {
                Cookie remember = cookie;
                remember.setValue(null);
                remember.setMaxAge(0);
                resp.addCookie(remember);
            }
        }
        req.getRequestDispatcher("/WEB-INF/ftl/home.ftl").forward(req, resp);
    }
}
