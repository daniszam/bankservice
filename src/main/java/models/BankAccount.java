package models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class BankAccount {
    private Long bankAccounNumber;
    private String typeBankAccount;
    private Float balance;
    private String codeWord;
    private User user;
    private Float percent;
}
