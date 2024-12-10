package com.restaurantmenu.digitalmenu.restaurant.srevice.impl;

import com.restaurantmenu.digitalmenu.restaurant.mapper.RestaurantMapper;
import com.restaurantmenu.digitalmenu.restaurant.model.requestDto.RestaurantCreateRequest;
import com.restaurantmenu.digitalmenu.restaurant.model.requestDto.RestaurantUpdateRequest;
import com.restaurantmenu.digitalmenu.restaurant.model.responseDto.RestaurantResponse;
import com.restaurantmenu.digitalmenu.restaurant.repository.RestaurantRepository;
import com.restaurantmenu.digitalmenu.restaurant.repository.entity.Restaurant;
import com.restaurantmenu.digitalmenu.restaurant.srevice.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;
    private RestaurantMapper restaurantMapper;


    /**
     * Add a list of new restaurants.
     *
     * @param restaurantCreateRequest the list of restaurants to be created.
     * @return a ResponseEntity with HTTP status 201 (Created) and the location of the created resource.
     * @throws IllegalArgumentException if validation fails for any of the restaurants.
     */
    @Override
    public void addRestaurants(@RequestBody List<RestaurantCreateRequest> restaurantCreateRequest) {
        // Convert the requests to Restaurant entities, including generating restaurantId
        List<Restaurant> restaurantList = restaurantMapper.createRequestToRestaurantList(restaurantCreateRequest);

        // Save all valid restaurant items directly
        restaurantRepository.saveAll(restaurantList);
    }


    /**
     * Updates a list of existing restaurants.
     *
     * @param restaurantUpdateRequest the list of restaurants with updates.
     * @return a ResponseEntity with HTTP status 204 (No Content) after successful update.
     * @throws IllegalArgumentException if validation fails or any restaurants does not exist.
     */
    @Override
    public boolean updateRestaurants(List<RestaurantUpdateRequest> restaurantUpdateRequest) {

        // Convert the update request to Restaurant entities
        List<Restaurant> updatedRestaurants = restaurantMapper.updateRequestToRestaurantList(restaurantUpdateRequest);

        // Extract the IDs of the restaurants to be updated
        List<Long> restaurantIds = updatedRestaurants.stream()
                .map(Restaurant::getId)
                .collect(Collectors.toList());

        // Fetch existing restaurants from the repository
        List<Restaurant> existingRestaurants = restaurantRepository.findAllById(restaurantIds);

        // Create a map for faster lookup of existing restaurants by ID
        Map<Long, Restaurant> existingRestaurantMap = existingRestaurants.stream()
                .collect(Collectors.toMap(Restaurant::getId, restaurant -> restaurant));

        boolean updated = false;

        // Loop through each restaurant in the update list and update the existing restaurant
        for (Restaurant updatedRestaurant : updatedRestaurants) {
            Restaurant existingRestaurant = existingRestaurantMap.get(updatedRestaurant.getId());

            if (existingRestaurant != null) {  // Only update if the restaurant exists
                boolean isRestaurantUpdated = false;

                // Update fields only if the value is not null and different from the existing value
                if (updatedRestaurant.getRestaurantName() != null && !updatedRestaurant.getRestaurantName().equals(existingRestaurant.getRestaurantName())) {
                    existingRestaurant.setRestaurantName(updatedRestaurant.getRestaurantName());
                    isRestaurantUpdated = true;
                }

                if (updatedRestaurant.getDescription() != null && !updatedRestaurant.getDescription().equals(existingRestaurant.getDescription())) {
                    existingRestaurant.setDescription(updatedRestaurant.getDescription());
                    isRestaurantUpdated = true;
                }

                if (updatedRestaurant.getLocation() != null && !updatedRestaurant.getLocation().equals(existingRestaurant.getLocation())) {
                    existingRestaurant.setLocation(updatedRestaurant.getLocation());
                    isRestaurantUpdated = true;
                }

                // If any field was updated, mark the restaurant as updated
                if (isRestaurantUpdated) {
                    updated = true;
                }
            }
        }

        // Save all updated restaurants back to the repository if any update occurred
        if (updated) {
            restaurantRepository.saveAll(existingRestaurants);
        }

        return updated;
    }


    /**
     * Retrieves a list of all restaurants.
     *
     * @return a ResponseEntity containing the list of all restaurants and HTTP status 200 (OK).
     */
    @Override
    public List<RestaurantResponse> viewRestaurants() {
        return restaurantMapper.restaurantToRestaurantResponse(restaurantRepository.findAll());
    }

}
