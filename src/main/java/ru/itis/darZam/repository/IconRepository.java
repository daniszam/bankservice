package ru.itis.darZam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.darZam.models.Icon;

public interface IconRepository extends JpaRepository<Icon, Long> {
}
