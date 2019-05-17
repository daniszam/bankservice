package ru.itis.darZam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.darZam.models.Transaction;

import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

    private String category;
    private String date;
    private Float price;
    private String balanceName;

    public TransactionDto(Transaction transaction){
        this.balanceName = transaction.getBalance().getName();
        this.category = transaction.getCategory().getName();
        this.price = transaction.getPrice();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        this.date = simpleDateFormat.format(transaction.getDateTime());
    }
}
