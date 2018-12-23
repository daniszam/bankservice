package utils;

import models.Category;
import models.Transaction;

import java.awt.*;
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

    public List<Category> getCategoryUtils(List<Transaction> transactions) {
        this.categoryPercentMap = new HashMap<>();
        for (int i = 0; i < transactions.size(); i++) {
            Category category = transactions.get(i).getCategory();
            category.setPercent(null);
            if (!this.categoryPercentMap.containsKey(category)) {
                categoryPercentMap.put(category, transactions.get(i).getPrice());
            } else {
                categoryPercentMap.put(category, categoryPercentMap.get(category) + transactions.get(i).getPrice());
            }
            sum += transactions.get(i).getPrice();
        }

        List<Category> categories = new ArrayList<>();
        for (Map.Entry entry : categoryPercentMap.entrySet()) {
            float percent = (Float) entry.getValue() / sum;
            percent = percent * 100;
            Category category = (Category) entry.getKey();
            categoryPercentMap.put(category, percent);
        }
        Map<Category, Float> categoryFloatMap = new HashMap<>();
        categoryFloatMap.putAll(categoryPercentMap);
        for (Map.Entry entry : categoryFloatMap.entrySet()) {
            Category category = (Category) entry.getKey();
            category.setPercent((Float) entry.getValue());
        }
        categories.addAll(categoryFloatMap.keySet());
        return categories;

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

    public static Category getRandomPercent(List<Category> categories, List<Transaction> transactions) {
        int random = (int) (categories.size() * Math.random());
        float randPercent = (categories.get(random)).getPercent().intValue();
        Category category = transactions.get(random).getCategory();
        category.setPercent(randPercent);
        return category;
    }
}
