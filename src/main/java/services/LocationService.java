package services;

import models.LocationUser;

import java.util.List;

public interface LocationService {
    List<LocationUser> getLocationsPriority (LocationUser locationUser);
    List<LocationUser> getCountrys();

}
