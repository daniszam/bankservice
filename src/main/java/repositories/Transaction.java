package repositories;

import java.util.List;

public interface Transaction {
    void saveTransactions (List<models.Transaction> transactions);
}
