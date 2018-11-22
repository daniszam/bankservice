package services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckIpAddressImplTest {

    CheckIpAddress checkIpAddress;

    @Before
    public void setUp() throws Exception {
        checkIpAddress = new CheckIpAddressImpl();
    }

    @Test
    public void getLocation() {
        checkIpAddress.getLocation("94.180.242.160");
    }
}