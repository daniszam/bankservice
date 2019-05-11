package ru.itis.darZam.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name="bank_account")
public class BankAccount extends Balance{
    private Long bankAccounNumber;
    private Float percent;

    @Builder
    public BankAccount(String name, Icon icon, Float balance,
                Long bankAccounNumber, Float percent, User user, Long id, Date upDate,
                       Float upSum, Set<Transaction> transactions, Set<Credit> credits ){
        super(id, upDate, balance, name, icon, user, upSum, transactions, credits);
        this.setName(name);
        this.bankAccounNumber = bankAccounNumber;
        this.percent = percent;
    }
}
