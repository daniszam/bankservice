package ru.itis.darZam.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.darZam.models.Balance;
import ru.itis.darZam.models.BankAccount;
import ru.itis.darZam.models.Card;
import ru.itis.darZam.models.Cash;

@Component
public class StringToBalanceConverter implements Converter<String, Balance> {
    @Override
    public Balance convert(String s) {
        switch (s) {
            case "card":
                return new Card();
            case "bank account":
                return new BankAccount();
            case "cash":
                return new Cash();
        }
        return null;
    }
}
