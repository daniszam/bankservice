package context;

import localization.Localization;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

public class LocaleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        Map<String, String> localeEn = Localization.loadLocalization("en");
        Map<String, String> localeRu = Localization.loadLocalization("ru");
        context.setAttribute("localeEn", localeEn);
        context.setAttribute("localeRu", localeRu);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
