package ru.itis.darZam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.darZam.models.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
