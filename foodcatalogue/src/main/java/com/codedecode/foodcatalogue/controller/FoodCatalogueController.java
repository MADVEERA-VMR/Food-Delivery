package com.codedecode.foodcatalogue.controller;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.service.FoodCatalogueService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItems")
    public ResponseEntity<FoodItemDTO> addFoodItems(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO foodItemDTOCreated = foodCatalogueService.addFoodItems(foodItemDTO);
        return  new ResponseEntity<>(foodItemDTOCreated, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchFoodItemById(@PathVariable Integer restaurantId) {
        System.out.println("Received restaurantId: " + restaurantId); // Debugging statement
        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }
}
