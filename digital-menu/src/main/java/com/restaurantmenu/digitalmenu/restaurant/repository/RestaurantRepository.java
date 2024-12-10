package com.restaurantmenu.digitalmenu.restaurant.repository;

import com.restaurantmenu.digitalmenu.restaurant.repository.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

//    // Check existence of item by normalized name
//    @Query("SELECT COUNT(m) > 0 FROM Menu m WHERE LOWER(TRIM(m.itemName)) = LOWER(TRIM(:itemName))")
//    boolean existsByItemNameIgnoreCaseAndTrimmed(@Param("itemName") String itemName);
}
