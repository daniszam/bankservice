package ru.itis.darZam.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card extends Balance{
    private String cardType;
    private Float percent;


    @Builder
    public Card(String name, Icon icon, Float balance,
                String cardType, Float percent, User user,
                Long id, Date upDate, Float upSum, Set<Transaction> transactions,
                Set<Credit> credits){
        super(id, upDate, balance,name, icon, user, upSum, transactions, credits);
        this.setName(name);
        this.cardType = cardType;
        this.percent = percent;
    }

}

