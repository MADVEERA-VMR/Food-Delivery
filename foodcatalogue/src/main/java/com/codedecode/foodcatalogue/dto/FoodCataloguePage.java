package com.codedecode.foodcatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {
    private Restaurant restaurant;
    private List<FoodItemDTO> foodItemsList;
}
