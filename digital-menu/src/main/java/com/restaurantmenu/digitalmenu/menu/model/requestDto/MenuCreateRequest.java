package com.restaurantmenu.digitalmenu.menu.model.requestDto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MenuCreateRequest {

    private String itemName;

    private String itemDescription;

    private String restaurantId;

    private Integer price;

    private Integer totalQuantity;





}
