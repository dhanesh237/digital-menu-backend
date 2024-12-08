package com.restaurantmenu.digitalmenu.service.impl;

import com.restaurantmenu.digitalmenu.mapper.MenuMapper;
import com.restaurantmenu.digitalmenu.model.requestDto.MenuCreateRequest;

import com.restaurantmenu.digitalmenu.model.requestDto.MenuUpdateRequest;
import com.restaurantmenu.digitalmenu.model.responseDto.MenuResponse;
import com.restaurantmenu.digitalmenu.repository.MenuRepository;
import com.restaurantmenu.digitalmenu.repository.entity.Menu;
import com.restaurantmenu.digitalmenu.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    private MenuMapper menuMapper;


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

    @Override
    public void updateItems(@RequestBody List<MenuUpdateRequest> menuUpdateRequest) {

        List<Menu> menuList = menuMapper.updateRequestToMenuList(menuUpdateRequest);

        // Fetch existing items by their IDs
        List<Long> itemIds = menuList.stream()
                .map(Menu::getId) // Assuming each Menu entity has an 'id' field
                .toList();

        List<Menu> existingMenus = menuRepository.findAllById(itemIds);

        // Update fields of existing items ------------ optimize
        existingMenus.forEach(existingMenu -> {
            menuList.stream()
                    .filter(menu -> menu.getId().equals(existingMenu.getId()))
                    .findFirst()
                    .ifPresent(menu -> {
                        // Update only non-null fields from the request
                        if (menu.getItemName() != null) {
                            existingMenu.setItemName(menu.getItemName());
                        }
                        if (menu.getItemDescription() != null) {
                            existingMenu.setItemDescription(menu.getItemDescription());
                        }
                        if (menu.getRestaurantId() != null) {
                            existingMenu.setRestaurantId(menu.getRestaurantId());
                        }
                        if (menu.getPrice() != null) {
                            existingMenu.setPrice(menu.getPrice());
                        }
                        if (menu.getTotalQuantity() != null) {
                            existingMenu.setTotalQuantity(menu.getTotalQuantity());
                        }
                    });
        });

        // Save updated items back to the repository
        menuRepository.saveAll(existingMenus);
    }

    @Override
    public List<MenuResponse> viewItems() {
        return menuMapper.menuToMenuResponse(menuRepository.findAll());
    }


}

