package com.restaurantmenu.digitalmenu.restaurant.srevice;

import com.restaurantmenu.digitalmenu.menu.model.responseDto.MenuResponse;
import com.restaurantmenu.digitalmenu.restaurant.model.requestDto.RestaurantCreateRequest;
import com.restaurantmenu.digitalmenu.restaurant.model.requestDto.RestaurantUpdateRequest;
import com.restaurantmenu.digitalmenu.restaurant.model.responseDto.RestaurantResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public interface RestaurantService {

    /**
     * Adds a list of new restaurants to the system.
     *
     * @param restaurantCreateRequest the list of restaurants to be added, containing necessary details like name, description, location, etc.
     * @throws IllegalArgumentException if any validation for restaurants creation fails.
     */
    void addRestaurants(@RequestBody List<RestaurantCreateRequest> restaurantCreateRequest);


    /**
     * Updates a list of existingrestaurants.
     *
     * @param restaurantUpdateRequest the list of restaurants with updates, including the IDs of the items to be updated.
     * @throws IllegalArgumentException if any restaurants does not exist or if validation fails.
     */
    boolean updateRestaurants(@RequestBody List<RestaurantUpdateRequest> restaurantUpdateRequest);


    /**
     * Retrieves all restaurants available in the system.
     *
     * @return a list of {@link MenuResponse} objects representing the current restaurants.
     */
    List<RestaurantResponse> viewRestaurants();
}
