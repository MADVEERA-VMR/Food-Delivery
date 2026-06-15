package com.codedecode.restaurantlisting.controller;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetchRestaurants(){
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurants();
        return new ResponseEntity<>(restaurantDTOList, HttpStatus.OK);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDTO> fetchRestaurantById(@PathVariable Integer id){
        return restaurantService.findRestaurantById(id);
        //return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> addRestaurants(@RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO savedRestaurantDTO = restaurantService.addRestaurantDB(restaurantDTO);
        return new ResponseEntity<>(savedRestaurantDTO, HttpStatus.CREATED);
    }
}
