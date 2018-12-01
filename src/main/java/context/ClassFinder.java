package context;

import java.util.Queue;

public interface ClassFinder {
    Queue<Class<?>> getClasses(String packageName);
}
