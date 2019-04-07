package services;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import lombok.NoArgsConstructor;
import models.LocationUser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

@NoArgsConstructor
@Service
public class CheckIpAddressImpl implements CheckIpAddress {

    private static final String DB_MMDB = "/Users/danis_zam/IdeaProjects/bankservice/src/main/webapp/resources/DBLocation/GeoLite2-City.mmdb";
    private Logger log = Logger.getLogger(CheckIpAddressImpl.class.getName());

    @Override
    public LocationUser getLocation(String ip) {
        LocationUser locationUser = LocationUser.builder()
                .country("Russia")
                .build();
        File database = new File(DB_MMDB);
        try {
            DatabaseReader databaseReader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse cityResponse = databaseReader.city(ipAddress);
            Country country = cityResponse.getCountry();
            City city = cityResponse.getCity();
            locationUser = LocationUser.builder()
                    .city(city.getName())
                    .country(country.getName())
                    .build();

        }catch (AddressNotFoundException e){
            log.log(Level.FINE, "address is uncorrect");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }



        return locationUser;
    }
}
