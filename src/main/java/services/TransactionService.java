package services;

import models.Balance;
import models.Transaction;
import org.json.JSONArray;

import java.util.List;

public interface TransactionService {
    List<Balance> getUsefulBalances(JSONArray balance);
    List<Transaction> getTransactions(JSONArray transactions);
}
