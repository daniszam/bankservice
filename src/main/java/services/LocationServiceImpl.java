package services;

import models.LocationUser;
import repositories.Location;
import repositories.LocationRepository;

import java.util.Collections;
import java.util.List;

public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @Override
    public List<LocationUser> getLocationsPriority(LocationUser locationUser) {
        List<LocationUser> locationUsers = locationRepository.getCountrys();
        if(locationUser.getCountry()==null) {
            locationUser.setCountry("Russia");
        }

        for (int i = 0 ; i<locationUsers.size(); i++){
            if(locationUsers.get(i).getCountry().equals(locationUser.getCountry())){
                Collections.swap(locationUsers, 0, i);
                return locationUsers;
            }
        }
        return locationUsers;
    }

    public List<LocationUser> getCountrys(){
        return locationRepository.getCountrys();
    }


}
