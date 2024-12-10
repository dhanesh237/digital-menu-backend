package com.restaurantmenu.digitalmenu.restaurant.mapper;

import com.restaurantmenu.digitalmenu.restaurant.model.requestDto.RestaurantCreateRequest;
import com.restaurantmenu.digitalmenu.restaurant.model.requestDto.RestaurantUpdateRequest;
import com.restaurantmenu.digitalmenu.restaurant.model.responseDto.RestaurantResponse;
import com.restaurantmenu.digitalmenu.restaurant.repository.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring") // "spring" allows Spring to inject this mapper
public interface RestaurantMapper {


    List<Restaurant> createRequestToRestaurantList(List<RestaurantCreateRequest> createRequestBody);

    @Mapping(target = "restaurantId", expression = "java(generateRestaurantId())")
    Restaurant createRequestToRestaurant(RestaurantCreateRequest request);

    // Helper method for generating the restaurantId
    default String generateRestaurantId() {
        return "REST-" + UUID.randomUUID();
    }

    @Mapping(source = "id", target = "id")
    List<Restaurant> updateRequestToRestaurantList(List<RestaurantUpdateRequest> updateRequestBody);

    List<RestaurantResponse> restaurantToRestaurantResponse(List<Restaurant> menuList);

}