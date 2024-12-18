package com.restaurantmenu.digitalmenu.menu.model.responseDto;


import lombok.Data;

import java.util.UUID;

@Data
public class MenuResponse {

    private Long id;

    private String itemName;

    private String itemDescription;

    private String restaurantId;

    private String restaurantName;

    private Integer price;

    private Integer totalQuantity;
}
