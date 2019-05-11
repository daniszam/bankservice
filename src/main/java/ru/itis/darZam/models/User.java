package ru.itis.darZam.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
//@ToString
//@EqualsAndHashCode
@Builder
@Table(name = "app_user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Boolean gender;

    private String phone;

    private Date birthday;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Balance> balances;

    private String country;

    private String city;

    private String img;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transactions;

}
