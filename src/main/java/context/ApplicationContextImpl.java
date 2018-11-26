package context;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContextImpl implements ApplicationContext {

    private static ApplicationContextImpl context;
    private Map<String, Object> components;
    private List<String> packages;

    static {
        context = new ApplicationContextImpl();
    }

    private ApplicationContextImpl() {
        components = new HashMap<>();
        components.put(DataSource.class.toString(),
                new DriverManagerDataSource(
                        "jdbc:postgresql://localhost:5432/bank_service",
                        "postgres",
                        "dREAM1cACAO"));

        packages = new ArrayList<>();

    }


    @SneakyThrows
    public void setComponents(List<String> packages) {
        this.packages.addAll(packages);
        while (packages.size() > 0) {
            List<Class<?>> classes = new ClassFinderImpl().getClasses(packages.remove(0));
            while (classes.size() > 0) {
                Class packageClass = classes.remove(0);
                Constructor[] constructors = packageClass.getConstructors();
                if (constructors.length > 0) {
                    Class[] parametrTypes = constructors[0].getParameterTypes();
                    List<Object> parametrs = new ArrayList<>();
                    for (Class parametr : parametrTypes) {
                        parametrs.add(getComponent(parametr));
                    }
                    components.put(packageClass.getName(), packageClass.
                            getConstructor(parametrTypes)
                            .newInstance(parametrs.toArray()));
                }
            }
        }
    }


    public <T> void putComponent(T component) {
        components.put(component.getClass().toString(), component);
    }

    @Override
    public <T> T getComponent(Class<T> componentClass) {
        for (Object component : components.values()) {
            if (componentClass.isAssignableFrom(component.getClass())) {
                return (T) component;
            }
        }
        return null;
    }

    public static ApplicationContextImpl getContext() {
        return context;
    }


}
