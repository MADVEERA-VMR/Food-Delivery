package com.codedecode.order.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @NotNull(message = "restaurant.id is required")
    private Integer id;
    @NotBlank(message = "restaurant.name is required")
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;

}
