package models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BankAccount extends Balance{
    private Long bankAccounNumber;
    private Float percent;
    private Date upDate;
    private Float upSum;

    @Builder
    public BankAccount(String name, Icon icon, Float balance,
                Long bankAccounNumber, Float percent, User user, Long id, Date upDate, Float upSum ){
        super(balance,name, icon, id, user);
        this.setName(name);
        this.bankAccounNumber = bankAccounNumber;
        this.percent = percent;
        this.upDate = upDate;
        this.upSum = upSum;
    }
}
