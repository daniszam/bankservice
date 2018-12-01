package services;

import lombok.Data;
import lombok.NoArgsConstructor;
import models.Balance;
import models.Category;
import models.Transaction;
import models.User;
import org.json.JSONArray;
import org.json.JSONObject;
import repositories.TransactionRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@NoArgsConstructor
@Data
public class TransactionServiceImpl implements TransactionService {
    private User user;
    private List<Balance> balances;
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(User user, TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        this.user = user;
        this.balances = new ArrayList<>();
    }

    @Override
    public List<Balance> getUsefulBalances(JSONArray balance) {
        for (int k = 0; k < balance.length(); k++) {
            JSONObject type = balance.getJSONObject(k);
            Balance balanceUse = user.getBalances().get(type.getInt("id"));
            balanceUse.setUser(null);
            if (balanceUse.getName() == null) {
                balanceUse.setName(balanceUse.getClass().getSimpleName());
            }
            balances.add(balanceUse);
        }
        return balances;
    }

    @Override
    public List<Transaction> getTransactions(JSONArray items) {
        int j = 0;
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            if (balances.size() < i) {
                j = 0;
            }
            String priceStr = items.getJSONObject(i).getString("value");
            float price = 0;
            Pattern pattern = Pattern.compile("[0-9]+([,.][0-9]+)?");
            Matcher matcher = pattern.matcher(priceStr);
            if (!matcher.find()) {
                price = 0;
            } else {
                price = Float.parseFloat(matcher.group());
            }
            Transaction transaction = Transaction
                    .builder()
                    .category(Category
                            .builder()
                            .id(items.getJSONObject(i).getLong("id"))
                            .name(items.getJSONObject(i).getString("name"))
                            .build())
                    .price(price)
                    .dateTime(new Date(new java.util.Date().getTime()))
                    .user(user)
                    .build();

            balances.get(j).setBalance(balances.get(j).getBalance() - transaction.getPrice());
            Balance jsonBalance = balances.get(j);
            jsonBalance.setUser(null);
            transactionRepository.save(transaction);
            transactionRepository.saveToBalance(transaction.getId(), jsonBalance);
            transactions.add(transaction);
            j++;
        }
        return transactions;
    }
}
