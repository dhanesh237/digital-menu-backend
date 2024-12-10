package com.restaurantmenu.digitalmenu.menu.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "menu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "itemName", nullable = false)
    private String itemName;

    @Column(name = "itemDescription", nullable = false)
    private String itemDescription;

    @Column(name = "restaurantId", nullable = false)
    private String restaurantId;

    @Column(name = "restaurantName", nullable = false)
    private String restaurantName;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "totalQuantity", nullable = false)
    private Integer totalQuantity;


}
