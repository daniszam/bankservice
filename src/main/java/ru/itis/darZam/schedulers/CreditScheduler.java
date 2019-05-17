package ru.itis.darZam.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itis.darZam.services.CreditService;


@Component
@EnableScheduling
public class CreditScheduler {

    @Autowired
    private CreditService creditService;
//   every day
    @Scheduled(cron = "0 0 12 * * ?")
//    @Scheduled(cron = "*/10 * * * * *")
    public void makeCreditTransaction(){
        creditService.executeTodayCredit();
    }
}
