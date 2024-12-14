package com.restaurantmenu.digitalmenu.menu.controller;

import com.restaurantmenu.digitalmenu.menu.model.requestDto.MenuCreateRequest;
import com.restaurantmenu.digitalmenu.menu.model.requestDto.MenuUpdateRequest;
import com.restaurantmenu.digitalmenu.menu.model.responseDto.MenuResponse;
import com.restaurantmenu.digitalmenu.menu.service.MenuService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
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

        // Call the service to update items
        boolean isUpdated = menuService.updateItems(menuUpdateRequest);

        // If the update is successful, return 204 No Content
        if (isUpdated) {
            return ResponseEntity.noContent().build();
        }

        // If no items were updated (perhaps due to invalid data or non-existing resource), return 404 Not Found
        return ResponseEntity.notFound().build();
    }


//    /**
//     * Retrieves a list of all menu items.
//     *
//     * @return a ResponseEntity containing the list of all menu items and HTTP status 200 (OK).
//     */
//    @GetMapping()
//    public ResponseEntity<List<MenuResponse>> viewMenuList() {
//
//        // Call the service to get all menu list
//        List<MenuResponse> menuList = menuService.viewItems();
//
//        // Return all menu list
//        return ResponseEntity.ok().body(menuList);
//    }


    /**
     * Retrieves a restaurant details by restaurantId and restaurantName.
     *
     * @return a ResponseEntity containing the list of all restaurants and HTTP status 200 (OK).
     */

    @GetMapping
    public ResponseEntity<List<MenuResponse>> viewMenus(
            @RequestParam(value = "restaurantId", required = false) String restaurantId,
            @RequestParam(value = "restaurantName", required = false) String restaurantName) throws BadRequestException {

        if (restaurantId != null && restaurantName != null) {
            // Fetch by ID and Name
            List<MenuResponse> menuList =
                    menuService.viewMenusByRestaurantNameAndRestaurantId(restaurantName, restaurantId);
            return ResponseEntity.ok(menuList);
        } else {
           throw new BadRequestException("restaurantId or restaurantName not be null");
        }
    }

}

