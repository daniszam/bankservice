package services;

import models.Balance;
import models.Category;
import models.Transaction;
import org.json.JSONArray;

import java.util.List;

public interface TransactionService {
    List<Balance> getUsefulBalances(JSONArray balance);
    List<Transaction> getTransactions(JSONArray transactions);
    List<Category> getCategorys();
    List<Category> getPercentCategory(List<Transaction> transactions);
}
