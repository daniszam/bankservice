package ru.itis.darZam.utils;

import ru.itis.darZam.models.Balance;
import ru.itis.darZam.models.Category;
import ru.itis.darZam.models.dto.CategoryDto;
import ru.itis.darZam.models.Transaction;
import ru.itis.darZam.models.dto.CategoryPercentDto;

import java.util.*;

public class CircleStatistik {


    public static Integer getPercentByUpSum(Set<Balance> balances){
        double sumOfsalary = 0;
        double sumOfCosts = 0;
        sumOfsalary = balances.stream().mapToDouble(Balance::getUpSum).sum();
        sumOfCosts = balances.stream().mapToDouble(Balance::getCount).sum();
        double remainder = sumOfsalary - sumOfCosts;
        return (int)(((sumOfCosts/sumOfsalary) * 100));
    }

    public static CategoryDto getRandomPercent(List<Category> categories, Set<Transaction> transactions){
        if (!categories.isEmpty()) {
            Random random = new Random();
            List<Transaction> transactionsList = new ArrayList<>(transactions);
            Category category = transactionsList.get(random.nextInt(transactions.size())).getCategory();
            double sumOfTransactionCosts = transactions.stream()
                    .filter(a -> a.getCategory().getId().equals(category.getId()))
                    .mapToDouble(Transaction::getPrice)
                    .sum();
            double sumOfAllTransactions = transactionsList.stream().mapToDouble(Transaction::getPrice).sum();
            return new CategoryDto(category, (sumOfTransactionCosts / sumOfAllTransactions) * 100);
        }
        return new CategoryDto(Category.builder().name("еще нет категорий").build(), 0);
    }

    public static CategoryDto getRandomPercent(List<CategoryPercentDto> categories){
        if (!categories.isEmpty()) {
            Random random = new Random();
            CategoryPercentDto randomCategory = categories.get(random.nextInt(categories.size()));
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(randomCategory.getId());
            categoryDto.setName(randomCategory.getName());
            categoryDto.setSumOfCosts(randomCategory.getPercent());
            return categoryDto;
        }
        return new CategoryDto(Category.builder().name("еще нет категорий").build(), 0);
    }


    public static List<CategoryDto> getPercentCategories(Set<Transaction> transactions){
        Map<Category, CategoryDto> categoryDtoMap = new HashMap<>();
        double sumOfTransactions = 0;
        for(Transaction transaction: transactions){
            if (categoryDtoMap.containsKey(transaction.getCategory())){
                CategoryDto categoryDto = categoryDtoMap.get(transaction.getCategory());
                categoryDto.setSumOfCosts((categoryDto.getSumOfCosts()+transaction.getPrice().intValue()));
            }else {
                categoryDtoMap.put(
                        transaction.getCategory(),
                        new CategoryDto(transaction.getCategory(), transaction.getPrice()));
            }
            sumOfTransactions += transaction.getPrice();
        }
        List<CategoryDto> categoryDtos = new ArrayList<>(categoryDtoMap.values());
        double finalSumOfTransactions = sumOfTransactions;
        categoryDtos.forEach(a->a.setSumOfCosts((int)((a.getSumOfCosts()/ finalSumOfTransactions)*100)));
        return categoryDtos;
    }

}
