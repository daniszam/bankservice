package services;

import models.LocationUser;

public interface CheckIpAddress {
    LocationUser getLocation(String ip);
}
