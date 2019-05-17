package ru.itis.darZam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "credit")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    private Date expirationDate;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "balance_id")
    @JsonIgnore
    private Balance balance;

}
