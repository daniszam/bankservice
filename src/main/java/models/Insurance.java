package models;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder


public class Insurance {
    private String typeInsurance;
    private Double priceMonth;
    private User user;
    private Date startDate;
    private Date finishDate;
    private Long id;

}

