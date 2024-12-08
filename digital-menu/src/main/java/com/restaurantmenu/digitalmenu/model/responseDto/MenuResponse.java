package com.restaurantmenu.digitalmenu.model.responseDto;


import lombok.Data;

import java.util.UUID;

@Data
public class MenuResponse {

    private Long id;

    private String itemName;

    private String itemDescription;

    private UUID restaurantId;

    private Integer price;

    private Integer totalQuantity;
}
