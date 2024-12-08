package com.restaurantmenu.digitalmenu.controller;

import com.restaurantmenu.digitalmenu.model.requestDto.MenuCreateRequest;
import com.restaurantmenu.digitalmenu.model.requestDto.MenuUpdateRequest;
import com.restaurantmenu.digitalmenu.model.responseDto.MenuResponse;
import com.restaurantmenu.digitalmenu.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/menus")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;


    /**
     * Adds a list of new menu items.
     *
     * @param menuCreateRequest the list of menu items to be created.
     * @return a ResponseEntity with HTTP status 201 (Created) and the location of the created resource.
     * @throws IllegalArgumentException if validation fails for any of the menu items.
     */
    @PostMapping
    public ResponseEntity<Void> addMenu(@RequestBody List<MenuCreateRequest> menuCreateRequest) {

        // Call the service to add items and return the location URI for created resources
      menuService.addItems(menuCreateRequest);

        // Return 201 Created with the Location header
        return ResponseEntity
                .created(URI.create("/api/v1/menus"))
                .build();
    }


    /**
     * Updates a list of existing menu items.
     *
     * @param menuUpdateRequest the list of menu items with updates.
     * @return a ResponseEntity with HTTP status 204 (No Content) after successful update.
     * @throws IllegalArgumentException if validation fails or any item does not exist.
     */
    @PatchMapping
    public ResponseEntity<Void> updateMenu(@RequestBody List<MenuUpdateRequest> menuUpdateRequest) {

        // Call the service to update items and return the location URI for updated resources
        menuService.updateItems(menuUpdateRequest);

        // Return 201 Created with the Location header
        return ResponseEntity
                .created(URI.create("/api/v1/menus"))
                .build();
    }


    /**
     * Retrieves a list of all menu items.
     *
     * @return a ResponseEntity containing the list of all menu items and HTTP status 200 (OK).
     */
    @GetMapping()
    public ResponseEntity<List<MenuResponse>> viewMenuList() {

        // Call the service to get all menu list
        List<MenuResponse> menuList = menuService.viewItems();

        // Return all menu list
        return ResponseEntity.ok().body(menuList);
    }


}

