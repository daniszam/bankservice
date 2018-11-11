package models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BankAccount extends Balance{
    private Long bankAccounNumber;
    private Float percent;
    private Date upDate;
    private Float upSum;

    @Builder
    public BankAccount(String name, String icon, Float balance,
                Long bankAccounNumber, Float percent, User user, Long id, Date upDate, Float upSum ){
        super(balance, icon, name, id, user);
        this.bankAccounNumber = bankAccounNumber;
        this.percent = percent;
        this.upDate = upDate;
        this.upSum = upSum;
    }
}
