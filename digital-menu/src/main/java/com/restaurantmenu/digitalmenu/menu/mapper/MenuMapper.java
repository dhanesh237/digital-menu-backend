package com.restaurantmenu.digitalmenu.menu.mapper;

import com.restaurantmenu.digitalmenu.menu.model.requestDto.MenuCreateRequest;
import com.restaurantmenu.digitalmenu.menu.model.requestDto.MenuUpdateRequest;
import com.restaurantmenu.digitalmenu.menu.model.responseDto.MenuResponse;
import com.restaurantmenu.digitalmenu.menu.repository.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // "spring" allows Spring to inject this mapper
public interface MenuMapper {

    Menu toMenu(MenuCreateRequest requestBody);

    List<Menu> createRequestToMenuList(List<MenuCreateRequest> createRequestBody);

    @Mapping(source = "id", target = "id")
    List<Menu> updateRequestToMenuList(List<MenuUpdateRequest> updateRequestBody);

    List<MenuResponse> menuToMenuResponse(List<Menu> menuList);

}