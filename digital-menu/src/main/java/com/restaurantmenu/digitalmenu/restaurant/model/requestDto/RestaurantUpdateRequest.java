package com.restaurantmenu.digitalmenu.restaurant.model.requestDto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RestaurantUpdateRequest {

    private Long id;

    private String restaurantName;

    private String description;

    private String restaurantId;

    private String location;

}