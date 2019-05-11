package ru.itis.darZam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.darZam.models.Credit;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM credit WHERE EXTRACT(DAY FROM startdate)= :start_day and expirationdate>NOW()")
    List<Credit> getAllByDay(@Param("start_day") int day);
}
