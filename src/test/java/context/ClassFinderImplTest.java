package context;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassFinderImplTest {

    ClassFinder classFinder;
    @Before
    public void setUp() throws Exception {
        classFinder = new ClassFinderImpl();
    }

    @Test
    public void getClasses() {
        System.out.println(classFinder.getClasses("repositories"));
    }
}