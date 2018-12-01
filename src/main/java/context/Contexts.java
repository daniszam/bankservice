package context;

public class Contexts {
    public static ApplicationDiContext primitive(){
        return ApplicationDiContextImpl.getContext();
    }
}
