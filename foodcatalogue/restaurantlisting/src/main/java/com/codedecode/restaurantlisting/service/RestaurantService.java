package com.codedecode.restaurantlisting.service;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepo restaurantRepo;

    @Autowired
    RestaurantMapper restaurantMapper;

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream()
                .map(restaurantMapper::mapRestaurantToRestaurantDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO addRestaurantDB(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO);
        restaurant = restaurantRepo.save(restaurant);
        return restaurantMapper.mapRestaurantToRestaurantDTO(restaurant);
    }

    public ResponseEntity<RestaurantDTO> findRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = Optional.ofNullable(restaurantRepo.findById(id).orElse(null));
        if (restaurant.isPresent()) {
            return new ResponseEntity<>(restaurantMapper.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND); // or throw an exception if you prefer to handle not found cases differently
        }
    }

}
