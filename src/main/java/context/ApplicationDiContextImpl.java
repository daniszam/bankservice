package context;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationDiContextImpl implements ApplicationDiContext {

    private static ApplicationDiContextImpl context;
    private Map<String, Object> components;
    private static final String CONFIG = "application/application.properties";
    private static final String COMPONENTS = "application/components.properties";


    static {
        context = new ApplicationDiContextImpl();
    }

    private ApplicationDiContextImpl() {
        components = new HashMap<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(CONFIG)) {
            Properties properties = new Properties();
            if (is != null) {
                properties.load(is);
            } else {
                throw new FileNotFoundException("property file '" + CONFIG + "' not found in the classpath");
            }
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
            components.put(JdbcTemplate.class.getName(), new JdbcTemplate(dataSource));
            components.put(DataSource.class.getName(), dataSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setComponents();
        setDependency();
    }

    public static ApplicationDiContextImpl getContext() {
        return context;
    }


    public <T> T getComponent(Class<T> componentClass) {
        for (Object component : components.values()) {
            if (componentClass.isAssignableFrom(component.getClass())) {
                return (T) component;
            }
        }
        return null;
    }

    private void setDependency(){
        for(Map.Entry entry: components.entrySet()){
            Object component = entry.getValue();
            for(Field field: component.getClass().getDeclaredFields()){
                try {
                    Object dependency = getComponent(field.getType());
                    if(dependency == null){
                        continue;
                    }
                    if(!field.isAccessible()){
                        field.setAccessible(true);
                        field.set(component,dependency);
                        field.setAccessible(false);
                    }else {
                        field.set(component,dependency);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setComponents() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(COMPONENTS)) {
            Properties properties = new Properties();
            if (is != null) {
                properties.load(is);
            } else {
                throw new FileNotFoundException
                        ("property file '" + COMPONENTS + "' not found in the classpath");
            }
            for (String pathToPackage : properties.stringPropertyNames()) {
                Queue<Class<?>> classes = new ClassFinderImpl().getClasses(pathToPackage);
                while (!classes.isEmpty()) {
                    Class component = classes.poll();
                    if (component.getConstructors().length > 0) {
                        components.put
                                (component.getName(), component.getConstructor().newInstance());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       ApplicationDiContextImpl app = new ApplicationDiContextImpl();
       for(Map.Entry entry:app.components.entrySet()){
           System.out.println(entry.getValue());
       }
    }
}
