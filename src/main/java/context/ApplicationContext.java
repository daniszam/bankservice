package context;

import java.util.List;

public interface ApplicationContext {
    <T> T getComponent(Class<T> componentClass);
    void setComponents(List<String> packages);
}
