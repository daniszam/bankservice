package models;

import lombok.*;

import java.time.Period;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Card {
    private String cardType;
    private Float perzent;
    private String validityPeriod;
    private User user;
    private Long id;
    private Float balance;
}

