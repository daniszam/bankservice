package ru.itis.darZam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.darZam.models.*;
import ru.itis.darZam.models.dto.BalanceUpdateDto;
import ru.itis.darZam.repository.BalanceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public List<String> getBalanceTypes(){
        List<String> balances = new ArrayList<>();
        balances.add("card");
        balances.add("bank account");
        balances.add("cash");
        return balances;
    }

    public List<BalanceUpdateDto> updateBalancesById(List<BalanceUpdateDto> balances){
        List<BalanceUpdateDto> balanceList = new ArrayList<>();
        for(BalanceUpdateDto balanceUpdateDto: balances){
            Long balanceId = balanceUpdateDto.getId();
            Float updateSum = balanceUpdateDto.getSum();
            Optional<Balance> balance = balanceRepository.findById(balanceId);
            balance.ifPresent(balance1 -> balance1.setCount(balance1.getCount() + updateSum));
            if (balance.isPresent()) {
                balanceRepository.save(balance.get());
                BalanceUpdateDto returnBalance = new BalanceUpdateDto();
                returnBalance.setId(balance.get().getId());
                returnBalance.setSum(balance.get().getCount());
                balanceList.add(returnBalance);
            }
        }
        return balanceList;
    }

    public void save(Balance balance){
        balanceRepository.save(balance);
    }

    public List<Balance> getAllByDto(List<BalanceUpdateDto> balanceUpdateDtos){
        return balanceUpdateDtos.stream().map(a->balanceRepository.getOne(a.getId())).collect(Collectors.toList());
    }
}
