package com.codedecode.foodcatalogue.service;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.Restaurant;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${clients.restaurant.fetch-by-id-url}")
    private String restaurantFetchByIdUrl;

    public FoodItemDTO addFoodItems(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItem);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {

        List<FoodItem> foodItems = fetchFoodItemList(restaurantId); // Fetch all food items for restaurantId
        Restaurant restaurant = fetchRestaurantDetails(restaurantId); // Fetch restaurant details for restaurantId
        return createFoodCataloguePage(foodItems, restaurant); // Create and return the FoodCataloguePage with the fetched data

    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItems, Restaurant restaurant) {
        return new FoodCataloguePage(
                restaurant, // Assuming you want to return a list of restaurants, even if it's just one
                foodItems.stream()
                        .map(FoodItemMapper.INSTANCE::mapFoodItemToFoodItemDTO)
                        .toList()
        );
    }

    private Restaurant fetchRestaurantDetails(Integer restaurantId) {
        String restaurantServiceUrl = restaurantFetchByIdUrl + restaurantId;
        return restTemplate.getForObject(restaurantServiceUrl, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
            return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
