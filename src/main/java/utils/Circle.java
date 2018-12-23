package utils;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.BankAccount;
import models.Card;

import java.util.List;

@Data
@NoArgsConstructor
public class Circle {
    private int percent;
    private List<Card> cards;
    private List<BankAccount> bankAccounts;
    private float sum = 0;
    private float countMoney = 0;


    public int getPercent(List<Card> cards, List<BankAccount> bankAccounts) {
        if (cards.size() == 0 && bankAccounts.size() == 0) {
            return 0;
        } else {
            this.cards = cards;
            this.bankAccounts = bankAccounts;
            for (int i = 0; i < cards.size(); i++) {
                sum += cards.get(i).getUpSum();
                countMoney += cards.get(i).getBalance();
            }
            for (int i = 0; i < bankAccounts.size(); i++) {
                sum += bankAccounts.get(i).getUpSum();
                countMoney += bankAccounts.get(i).getBalance();
            }
            if (sum == 0) {
                if (countMoney < 0) {
                    percent = -100;
                    return percent;
                }
                if (countMoney > 0) {
                    percent = 100;
                    return percent;
                }
            }
            float a = ((sum - (sum - Math.abs(countMoney))) / sum);
            float percent = a * 100;
            this.percent = (int) percent;
            return this.percent;
        }
    }

    public void updatePercent() {
        if (sum == 0) {
            return;
        }
        if (cards.size() == 0 && bankAccounts.size() == 0) {
            return;
        } else {
            float a = ((sum - (sum - Math.abs(countMoney))) / sum);
            float percent = a * 100;
            System.out.println(sum + " " + percent);
            this.percent = (int) percent;
        }
    }
}
