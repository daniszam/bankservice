package ru.itis.darZam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.darZam.models.Category;
import ru.itis.darZam.models.Transaction;
import ru.itis.darZam.models.dto.CategoryDto;
import ru.itis.darZam.models.User;
import ru.itis.darZam.models.dto.CategoryPercentDto;
import ru.itis.darZam.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategoryByUser(User user) {
        return categoryRepository.getByUser(user.getId());
    }

    public List<CategoryPercentDto> getCategoryWithPercent(User user) {
        List<CategoryPercentDto> categoryPercentDtoList = new ArrayList<>();
        List<Category> userCategories = getCategoryByUser(user);
        Set<Transaction> userTransactions = user.getTransactions();
        double sumOfAllTransaction = userTransactions.stream().mapToDouble(Transaction::getPrice).sum();

        for (Category category : userCategories) {
            Set<Transaction> transactionsByCategory = userTransactions.stream()
                    .filter(a -> a.getCategory().getId().equals(category.getId()))
                    .collect(Collectors.toSet());

            double sumOfTransactionByCategory = transactionsByCategory.stream().mapToDouble(Transaction::getPrice).sum();
            categoryPercentDtoList.add(
                    CategoryPercentDto.builder()
                            .name(category.getName())
                            .id(category.getId())
                            .percent((int) (sumOfTransactionByCategory / sumOfAllTransaction) * 100)
                            .build());
        }
        return categoryPercentDtoList;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> getAllByDto(List<CategoryDto> categoryDtos) {
        return categoryDtos.stream().map(a -> categoryRepository.getOne(a.getId())).collect(Collectors.toList());
    }

}
