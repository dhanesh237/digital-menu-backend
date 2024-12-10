package com.restaurantmenu.digitalmenu.restaurant.model.responseDto;


import lombok.Data;

import java.util.UUID;

@Data
public class RestaurantResponse {

    private Long id;

    private String restaurantName;

    private String description;

    private String restaurantId;

    private String location;
}
