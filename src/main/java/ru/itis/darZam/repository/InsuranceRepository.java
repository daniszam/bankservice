package ru.itis.darZam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.darZam.models.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}
