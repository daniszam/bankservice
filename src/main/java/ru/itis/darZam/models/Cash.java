package ru.itis.darZam.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Set;


@NoArgsConstructor
@Data
@Entity
@Table(name = "cash")
public class Cash extends Balance {

    @Builder
    public Cash(String name, Icon icon, Float balance, Long id, User user,
                Float upSum, Set<Transaction> transactions, Date upDate,
                Set<Credit> credits){
        super(id, upDate, balance, name, icon, user, upSum, transactions, credits);
    }
}
