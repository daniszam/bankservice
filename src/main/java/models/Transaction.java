package models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Transaction {
    private Date dateTime;
    private BankAccount fromAccount;
    private BankAccount toAccount;
    private Double transfer;
    private Long id;
}
