package ru.itis.darZam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.darZam.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
