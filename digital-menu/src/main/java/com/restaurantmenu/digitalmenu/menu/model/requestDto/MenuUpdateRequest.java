package com.restaurantmenu.digitalmenu.menu.model.requestDto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MenuUpdateRequest {

    private Long id;

    private String itemName;

    private String itemDescription;

    private String restaurantId;

    private String restaurantName;

    private Integer price;

    private Integer totalQuantity;



}