package com.codedecode.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOFrontE {

    private Integer userId;
    @Valid
    @NotNull(message = "restaurant is required")
    private Restaurant restaurant;
    @Valid
    List<FoodItemDTO> foodItemsList;

}
