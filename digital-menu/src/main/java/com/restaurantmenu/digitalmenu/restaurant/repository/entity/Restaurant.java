package com.restaurantmenu.digitalmenu.restaurant.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "restaurant")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "restaurantName", nullable = false)
    private String restaurantName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "restaurantId", nullable = false)
    private String restaurantId;

    @Column(name = "location", nullable = false)
    private String location;

}
