package com.restaurantmenu.digitalmenu.model.requestDto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MenuCreateRequest {

    private String itemName;

    private String itemDescription;

    private UUID restaurantId;

    private Integer price;

    private Integer totalQuantity;





}