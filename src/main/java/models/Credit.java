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
    private CreditType type;
    private User user;
    private Long id;

}
