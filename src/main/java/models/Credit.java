package models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Credit {
    private Date expirationDate;
    private Float percent;
    private String type;
    private User user;
    private Long id;

}
