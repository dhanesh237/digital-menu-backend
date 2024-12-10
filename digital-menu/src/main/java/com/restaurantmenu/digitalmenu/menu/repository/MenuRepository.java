package com.restaurantmenu.digitalmenu.menu.repository;

import com.restaurantmenu.digitalmenu.menu.repository.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    // Check existence of item by normalized name
    @Query("SELECT COUNT(m) > 0 FROM Menu m WHERE LOWER(TRIM(m.itemName)) = LOWER(TRIM(:itemName))")
    boolean existsByItemNameIgnoreCaseAndTrimmed(@Param("itemName") String itemName);
}
