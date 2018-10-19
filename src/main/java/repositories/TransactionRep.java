package repositories;


import models.BankAccount;

import java.util.List;

public interface TransactionRep<T> {
    List<T> searchByAccount(BankAccount bankAccount);
    void deleteByAccount(Long id);
}
