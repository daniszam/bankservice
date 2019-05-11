package ru.itis.darZam.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.darZam.models.Credit;
import ru.itis.darZam.repository.BalanceRepository;
import ru.itis.darZam.repository.CreditRepository;
import ru.itis.darZam.repository.TransactionRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public List<Credit> getAllByDay(int day){
        List<Credit> updateToday = creditRepository.getAllByDay(day);
        return updateToday;
    }

    public void executeTodayCredit(){
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        int day = now.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);
        List<Credit> creditsDTO = getAllByDay(day);
        System.out.println(creditsDTO);
        List<Credit> credits = creditsDTO.stream().
                map(a->creditRepository.getOne(a.getId())).collect(Collectors.toList());
        System.out.println("\n");
        System.out.println(creditsDTO.get(0).getBalance().getCount());
        credits.forEach(a->a.getBalance().setCount(
                a.getBalance().getCount()-a.getPrice()
        ));
        System.out.println("32");
        credits.stream().map(Credit::getBalance).forEach(a->balanceRepository.save(a));
    }

}
