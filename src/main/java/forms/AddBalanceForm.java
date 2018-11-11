package forms;

import lombok.Builder;
import lombok.Data;
import models.Balance;
import models.User;

@Builder
@Data
public class AddBalanceForm {
    private String date;
    private String sum;
    private String upSum;
    private Balance balance;
    private User user;
}
