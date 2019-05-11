package ru.itis.darZam.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

@Table(name = "insurance")
@Entity
public class Insurance {

    private String typeInsurance;

    private Double priceMonth;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date startDate;

    private Date finishDate;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

}

