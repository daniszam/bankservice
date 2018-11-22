package utils;

import models.Category;
import models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryPercent {
    private Map<Category, Float> categoryPercentMap;
    private Float sum;


    public CategoryPercent() {
        this.categoryPercentMap = new HashMap<>();
        sum = 0F;
    }

    public Map<Category, Float> getCategoryUtils(List<Transaction> transactions) {
        for (int i = 0; i < transactions.size(); i++) {
            Category category = transactions.get(i).getCategory();
            if (!this.categoryPercentMap.containsKey(category)) {
                categoryPercentMap.put(category, transactions.get(i).getPrice());
            } else {
                categoryPercentMap.put(category, categoryPercentMap.get(category) + transactions.get(i).getPrice());
            }
            sum += transactions.get(i).getPrice();
        }

        for (Map.Entry entry : categoryPercentMap.entrySet()) {
            float percent = (Float) entry.getValue() / sum;
            percent = percent * 100;

            categoryPercentMap.put((Category) entry.getKey(), percent);
        }

        return categoryPercentMap;

    }

    public List<CategoryUtil> getCategoryUtilsList(List<Transaction> transactions) {
        this.getCategoryUtils(transactions);
        List<CategoryUtil> categoryUtilList = new ArrayList<>();
        for (Map.Entry entry : categoryPercentMap.entrySet()) {
            categoryUtilList.add(CategoryUtil.builder()
                    .category((Category) entry.getKey())
                    .percent((Float) entry.getValue())
                    .build());

        }
        return categoryUtilList;
    }
}
