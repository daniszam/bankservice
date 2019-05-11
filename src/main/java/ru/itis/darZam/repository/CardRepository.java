package ru.itis.darZam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.darZam.models.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
