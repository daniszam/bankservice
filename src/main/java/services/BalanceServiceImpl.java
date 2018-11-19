package services;

import forms.AddBalanceForm;
import lombok.SneakyThrows;
import models.*;
import repositories.BankAccountRepository;
import repositories.CardRepository;
import repositories.CashRepository;
import repositories.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BalanceServiceImpl implements BalanceService{
    private DataSource dataSource;

    public BalanceServiceImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    @SneakyThrows
    public void add(AddBalanceForm addBalanceForm) {
        float upSum = 0;
        float sum = 0;
        if(addBalanceForm.getSum()==null){
            addBalanceForm.setSum("0");
            addBalanceForm.setDate(null);
        }
        if(addBalanceForm.getUpSum()==null){
            addBalanceForm.setUpSum("0");
            addBalanceForm.setDate(null);

        }
        Pattern pattern = Pattern.compile("[0-9]+([,.][0-9]+)?");
        Matcher matcher = pattern.matcher(addBalanceForm.getSum());
        if(!matcher.find()){
            addBalanceForm.setSum("0");
            addBalanceForm.setDate(null);
        }else {
            addBalanceForm.setSum(matcher.group());
        }
        matcher = pattern.matcher(addBalanceForm.getUpSum());
        if(!matcher.find()){
            addBalanceForm.setUpSum("0");
            addBalanceForm.setDate(null);
        }else{
            addBalanceForm.setUpSum(matcher.group());
        }

        if(addBalanceForm.getUpSum()!=null) {
            upSum = Float.parseFloat(addBalanceForm.getUpSum());
        }
        if(addBalanceForm.getSum()!=null) {
            sum = Float.parseFloat(addBalanceForm.getSum());
        }
        Date date = null;
        if(addBalanceForm.getDate()!=null) {
            if (addBalanceForm.getDate().length() > 10) {
                String date1 = addBalanceForm.getDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = (new Date(simpleDateFormat.parse(date1).getTime()));
            }
        }
        if(addBalanceForm.getUpSum() != null &&
                !addBalanceForm.getUpSum().equals("0") &&
                addBalanceForm.getDate()==null){
            date = new Date(new java.util.Date().getTime());
        }
        Balance balance = addBalanceForm.getBalance();
        balance.setUser(addBalanceForm.getUser());
        balance.setIcon(addBalanceForm.getIcon());
        balance.setName(addBalanceForm.getName());
        balance.setBalance(sum);
        if(balance.getClass().equals(Card.class)){
            CardRepository cardRepository = new CardRepository(dataSource);
            Card card =Card.builder()
                        .icon(balance.getIcon())
                        .upSum(upSum)
                        .upDate(date)
                        .name(balance.getName())
                        .balance(balance.getBalance())
                        .user(balance.getUser())
                        .build();

            cardRepository.save(card);
            addBalanceForm.getUser().getBalances().add(card);
        }

        if(balance.getClass().equals(BankAccount.class)){
            BankAccountRepository bankAccountRepository = new BankAccountRepository(dataSource);
            BankAccount bankAccount = BankAccount.builder()
                    .icon(balance.getIcon())
                    .balance(balance.getBalance())
                    .upSum(upSum)
                    .upDate(date)
                    .name(balance.getName())
                    .user(balance.getUser())
                    .build();

            bankAccountRepository.save(bankAccount);
            addBalanceForm.getUser().getBalances().add(bankAccount);

        }

        if(balance.getClass().equals(Cash.class)){
            CashRepository cashRepository = new CashRepository(dataSource);
            Cash cash = Cash.builder()
                    .balance(balance.getBalance())
                    .icon(balance.getIcon())
                    .user(balance.getUser())
                    .name(balance.getName())
                    .build();
            cashRepository.save(cash);
            addBalanceForm.getUser().getBalances().add(cash);
        }
    }

    @Override
    public void increaseBalance(Balance balance) {
        if(balance.getClass().equals(BankAccount.class)){
            BankAccountRepository bankAccountRepository = new BankAccountRepository(dataSource);
            bankAccountRepository.update((BankAccount)balance);
        }
        if(balance.getClass().equals(Card.class)){
            CardRepository cardRepository = new CardRepository(dataSource);
            cardRepository.update((Card)balance);
        }
        if(balance.getClass().equals(Cash.class)){
            CashRepository cashRepository = new CashRepository(dataSource);
            cashRepository.update((Cash)balance);
        }
    }
}
