package ru.itis.darZam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
//@ToString
//@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
@Entity
public class Transaction {

    private Date dateTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    private Float price;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "balance_id")
    @JsonIgnore
    private Balance balance;
}
