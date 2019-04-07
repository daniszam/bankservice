package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import context.Contexts;
import models.LocationUser;
import org.springframework.stereotype.Component;
import repositories.LocationRepository;
import services.CheckIpAddress;
import services.CheckIpAddressImpl;
import services.LocationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private LocationService locationService;
    private LocationRepository locationRepository;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if(ip==null) {
            ip = request.getRemoteAddr();
        }
        CheckIpAddress checkIpAddress = new CheckIpAddressImpl();
        LocationUser locationUser = checkIpAddress.getLocation(ip);

        List<LocationUser> countrys = locationService.getLocationsPriority(locationUser);
        List<LocationUser> citys = locationRepository.getCitys(countrys.get(0));
        request.setAttribute("countrys", countrys);
        request.setAttribute("citys", citys);
        request.getRequestDispatcher("/WEB-INF/JSP/Edit.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocationUser locationUser = LocationUser.builder()
                .country(req.getParameter("country"))
                .build();
        List<LocationUser> citys = locationRepository.getCitys(locationUser);
        ObjectMapper objectMapper = new ObjectMapper();
        String citysJson = objectMapper.writeValueAsString(citys);
        resp.setContentType("application/json");
        resp.getWriter().write(citysJson);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
//        locationService = Contexts.primitive().getComponent(LocationService.class);
//        locationRepository = Contexts.primitive().getComponent(LocationRepository.class);
    }
}
