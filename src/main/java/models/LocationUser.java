package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationUser {
    private String country;
    private String city;
}
