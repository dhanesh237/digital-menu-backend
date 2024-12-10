package com.restaurantmenu.digitalmenu.menu.service;

import com.restaurantmenu.digitalmenu.menu.model.requestDto.MenuCreateRequest;
import com.restaurantmenu.digitalmenu.menu.model.requestDto.MenuUpdateRequest;
import com.restaurantmenu.digitalmenu.menu.model.responseDto.MenuResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public interface MenuService {


     /**
      * Adds a list of new menu items to the system.
      *
      * @param menuCreateRequest the list of menu items to be added, containing necessary details like name, description, price, etc.
      * @throws IllegalArgumentException if any validation for menu creation fails.
      */
     void addItems(@RequestBody List<MenuCreateRequest> menuCreateRequest);


     /**
      * Updates a list of existing menu items.
      *
      * @param menuUpdateRequests the list of menu items with updates, including the IDs of the items to be updated.
      * @throws IllegalArgumentException if any menu item does not exist or if validation fails.
      */
     boolean updateItems(@RequestBody List<MenuUpdateRequest> menuUpdateRequests);


     /**
      * Retrieves all menu items available in the system.
      *
      * @return a list of {@link MenuResponse} objects representing the current menu items.
      */
     List<MenuResponse> viewItems();
}
