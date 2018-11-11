package models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Transaction {
    private Date dateTime;
    private Category category;
    private Float price;
    private Long id;
    private User user;
}
