package com.restaurantmenu.digitalmenu.menu.service.impl;

import com.restaurantmenu.digitalmenu.menu.mapper.MenuMapper;
import com.restaurantmenu.digitalmenu.menu.model.requestDto.MenuCreateRequest;

import com.restaurantmenu.digitalmenu.menu.model.requestDto.MenuUpdateRequest;
import com.restaurantmenu.digitalmenu.menu.model.responseDto.MenuResponse;
import com.restaurantmenu.digitalmenu.menu.repository.MenuRepository;
import com.restaurantmenu.digitalmenu.menu.repository.entity.Menu;
import com.restaurantmenu.digitalmenu.menu.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    private MenuMapper menuMapper;


    /**
     * Adds a list of new menu items.
     *
     * @param menuCreateRequest the list of menu items to be created.
     * @throws IllegalArgumentException if validation fails for any of the menu items.
     */
    @Override
    public void addItems(@RequestBody List<MenuCreateRequest> menuCreateRequest) {
        // Convert the requests to Menu entities
        List<Menu> menuList = menuMapper.createRequestToMenuList(menuCreateRequest);

        // Check for duplicates in the database
        for (Menu menu : menuList) {
            String normalizedItemName = menu.getItemName().trim().toLowerCase();
            boolean exists = menuRepository.existsByItemNameIgnoreCaseAndTrimmed(normalizedItemName);

            if (exists) {
                throw new IllegalArgumentException("Item with name '" + menu.getItemName() + "' already exists.");
            }
        }

        // Save all valid menu items
        menuRepository.saveAll(menuList);
    }


    /**
     * Updates a list of existing menu items.
     *
     * @param menuUpdateRequest the list of menu items with updates.
     * @return
     * @throws IllegalArgumentException if validation fails or any item does not exist.
     */
    @Override
    public boolean updateItems(@RequestBody List<MenuUpdateRequest> menuUpdateRequest) {

        // Convert update requests to Menu entities
        List<Menu> menuList = menuMapper.updateRequestToMenuList(menuUpdateRequest);

        // Fetch existing menus from the database by their IDs
        List<Long> itemIds = menuList.stream()
                .map(Menu::getId)
                .toList();

        // Fetch existing menus in bulk
        List<Menu> existingMenus = menuRepository.findAllById(itemIds);

        // Create a map for faster look-up of existing menus by ID
        Map<Long, Menu> existingMenuMap = existingMenus.stream()
                .collect(Collectors.toMap(Menu::getId, menu -> menu));

        // Update only non-null fields for existing items
        boolean updated = false;
        for (Menu menu : menuList) {
            Menu existingMenu = existingMenuMap.get(menu.getId());

            if (existingMenu != null) {  // Only update if the menu exists
                boolean menuUpdated = false;

                if (menu.getItemName() != null && !menu.getItemName().equals(existingMenu.getItemName())) {
                    existingMenu.setItemName(menu.getItemName());
                    menuUpdated = true;
                }

                if (menu.getItemDescription() != null && !menu.getItemDescription().equals(existingMenu.getItemDescription())) {
                    existingMenu.setItemDescription(menu.getItemDescription());
                    menuUpdated = true;
                }

                if (menu.getRestaurantId() != null && !menu.getRestaurantId().equals(existingMenu.getRestaurantId())) {
                    existingMenu.setRestaurantId(menu.getRestaurantId());
                    menuUpdated = true;
                }

                if (menu.getPrice() != null && !menu.getPrice().equals(existingMenu.getPrice())) {
                    existingMenu.setPrice(menu.getPrice());
                    menuUpdated = true;
                }

                if (menu.getTotalQuantity() != null && !menu.getTotalQuantity().equals(existingMenu.getTotalQuantity())) {
                    existingMenu.setTotalQuantity(menu.getTotalQuantity());
                    menuUpdated = true;
                }

                // If any field is updated, mark the menu as updated
                if (menuUpdated) {
                    updated = true;
                }
            }
        }

        // If there are updates, save all the updated items back to the repository
        if (updated) {
            menuRepository.saveAll(existingMenus);
        }

        return updated;
    }


    /**
     * Retrieves a list of all menu items.
     *
     * @return a ResponseEntity containing the list of all menu items and HTTP status 200 (OK).
     */
    @Override
    public List<MenuResponse> viewItems() {
        return menuMapper.menuToMenuResponse(menuRepository.findAll());
    }


}

