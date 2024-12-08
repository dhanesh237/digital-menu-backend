package com.restaurantmenu.digitalmenu.mapper;

import com.restaurantmenu.digitalmenu.model.requestDto.MenuCreateRequest;
import com.restaurantmenu.digitalmenu.model.requestDto.MenuUpdateRequest;
import com.restaurantmenu.digitalmenu.model.responseDto.MenuResponse;
import com.restaurantmenu.digitalmenu.repository.entity.Menu;
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