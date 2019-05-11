package ru.itis.darZam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Balance{

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;

    private Date upDate;

    private Float count;

    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "icon_id")
    private Icon icon;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private Float upSum;

    @OneToMany(mappedBy = "balance")
    @JsonIgnore
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "balance")
    @JsonIgnore
    private Set<Credit> creditSet;
}
