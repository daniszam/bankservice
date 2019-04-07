package services;

import lombok.Data;
import lombok.NoArgsConstructor;
import models.Balance;
import models.Category;
import models.Transaction;
import models.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CategoryRepository;
import repositories.TransactionRepository;
import utils.CategoryPercent;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@NoArgsConstructor
@Data
@Service
public class TransactionServiceImpl implements TransactionService {


    private User user;
    private List<Balance> balances;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;


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
                            .color(new Color((int)Math.random()*10000))
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

    @Override
    public List<Category> getCategorys() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getPercentCategory(List<Transaction> transactions) {
        CategoryPercent categoryPercent = new CategoryPercent();
        return categoryPercent.getCategoryUtils(transactions);
    }
}
