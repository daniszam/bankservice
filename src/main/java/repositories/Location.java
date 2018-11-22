package repositories;

import models.LocationUser;

import java.util.List;

public interface Location {
    List<LocationUser> getCitys(LocationUser country);
    List<LocationUser> getCountrys();

}
