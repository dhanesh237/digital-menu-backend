package com.restaurantmenu.digitalmenu.restaurant.controller;


import com.restaurantmenu.digitalmenu.restaurant.model.requestDto.RestaurantCreateRequest;
import com.restaurantmenu.digitalmenu.restaurant.model.requestDto.RestaurantUpdateRequest;
import com.restaurantmenu.digitalmenu.restaurant.model.responseDto.RestaurantResponse;
import com.restaurantmenu.digitalmenu.restaurant.srevice.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;


    /**
     * Add a list of new restaurants.
     *
     * @param restaurantCreateRequest the list of restaurants to be created.
     * @return a ResponseEntity with HTTP status 201 (Created) and the location of the created resource.
     * @throws IllegalArgumentException if validation fails for any of the restaurants.
     */
    @PostMapping
    public ResponseEntity<Void> addRestaurants(@RequestBody List<RestaurantCreateRequest> restaurantCreateRequest) {

        // Call the service to add items and return the location URI for created resources
        restaurantService.addRestaurants(restaurantCreateRequest);

        // Return 201 Created with the Location header
        return ResponseEntity
                .created(URI.create("/api/v1/restaurants"))
                .build();
    }


    /**
     * Updates a list of existing restaurants.
     *
     * @param restaurantUpdateRequest the list of restaurants with updates.
     * @return a ResponseEntity with HTTP status 204 (No Content) after successful update.
     * @throws IllegalArgumentException if validation fails or any restaurants does not exist.
     */
    @PatchMapping
    public ResponseEntity<Void> updateRestaurant(@RequestBody List<RestaurantUpdateRequest> restaurantUpdateRequest) {

        // Call the service to update restaurants
        boolean isUpdated = restaurantService.updateRestaurants(restaurantUpdateRequest);

        // If the update is successful, return 204 No Content
        if (isUpdated) {
            return ResponseEntity.noContent().build();
        }

        // If no restaurants were updated (perhaps due to invalid data or non-existing resource), return 404 Not Found
        return ResponseEntity.notFound().build();
    }



    /**
     * Retrieves a list of all restaurants.
     *
     * @return a ResponseEntity containing the list of all restaurants and HTTP status 200 (OK).
     */
    @GetMapping()
    public ResponseEntity<List<RestaurantResponse>> viewRestaurantList() {

        // Call the service to get all menu list
        List<RestaurantResponse> restaurantList = restaurantService.viewRestaurants();

        // Return all menu list
        return ResponseEntity.ok().body(restaurantList);
    }


}

