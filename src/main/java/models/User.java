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
    private Character gender;
    private String phoneNumber;
    private Date birthday;
    private List<BankAccount> bankAccounts;
    private String email;
    private String hashPassword;
    private List<Card> cards;
    private List<Credit> credits;
    private List<Insurance> insurances;

}
