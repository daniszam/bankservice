package ru.itis.darZam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.darZam.models.Category;
import ru.itis.darZam.models.User;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true ,
            value = "SELECT * FROM category c  WHERE EXISTS(SELECT 1 FROM transaction  WHERE transaction.category_id=c.id and transaction.user_id=:userId)" +
                    "GROUP BY c.id")
    List<Category> getByUser(@Param("userId") Long userId);


}
