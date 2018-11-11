package models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Card extends Balance{
    private String cardType;
    private Float percent;
    private Date upDate;
    private Float upSum;

    @Builder
    public Card(String name, String icon, Float balance,
                String cardType, Float percent, User user, Long id, Date upDate, Float upSum){
        super(balance, icon, name, id, user);
        this.cardType = cardType;
        this.percent = percent;
        this.upDate = upDate;
        this.upSum = upSum;
    }

}

