package models;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Short gender;
    private String phoneNumber;
    private Date birthday;
    private String email;
    private String hashPassword;
    private List<Credit> credits;
    private List<Balance> balances;
    private List<Transaction> transactions;
    private String country;
    private String city;
    private String img;

}
