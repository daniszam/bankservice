package ru.itis.darZam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.darZam.models.*;
import ru.itis.darZam.models.dto.*;
import ru.itis.darZam.repository.TransactionRepository;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private TransactionRepository transactionRepository;


    public void update(MySpaceBalanceDto mySpaceBalanceDto){
        List<BalanceUpdateDto> balanceUpdateDtoList = mySpaceBalanceDto.getType();
        System.out.println(balanceUpdateDtoList);
        List<CategoryDto> categoryDtos = mySpaceBalanceDto.getItems();
        System.out.println(categoryDtos);
        double sum = categoryDtos.stream().mapToDouble(CategoryDto::getSumOfCosts).sum();

        List<Category> categories = categoryService.getAllByDto(categoryDtos);
        List<Balance> balanceList = balanceService.getAllByDto(balanceUpdateDtoList);
        float averageSum = (float)(sum/balanceList.size());
        int categoriesMaxIndex = categories.size() - 1 ;

        for(Balance balance: balanceList){
            transactionRepository.save(
                    Transaction.builder()
                        .balance(balance)
                        .category(categories.get(categoriesMaxIndex))
                        .user(balance.getUser())
                        .dateTime(new Date())
                        .price(averageSum)
                        .build()
            );
            categoriesMaxIndex = categoriesMaxIndex > categories.size() - 1 ? 0 :categoriesMaxIndex;
            balance.setCount((balance.getCount()-averageSum));
            balanceService.save(balance);
        }
    }
}
