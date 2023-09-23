package com.pratheek.foodcatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDto {

    private int id;
    private String item;
    private String itemDescription;
    private boolean isVeg;
    private Number price;
    private Integer restaurantId;
    private Integer quantity;

}
