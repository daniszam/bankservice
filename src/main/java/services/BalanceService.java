package services;

import forms.AddBalanceForm;
import models.Balance;

public interface BalanceService {
    void add(AddBalanceForm addBalanceForm);
    void increaseBalance(Balance balance);
}
