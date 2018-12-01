package context;

public interface ApplicationDiContext {
    <T> T getComponent(Class<T> componentClass);
}
