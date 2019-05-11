package ru.itis.darZam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.darZam.models.Cash;

public interface CashRepository extends JpaRepository<Cash, Long> {

}
