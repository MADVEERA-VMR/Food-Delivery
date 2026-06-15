package com.codedecode.order.service;

import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFrontE;
import com.codedecode.order.dto.UserDTO;
import com.codedecode.order.entity.Order;
import com.codedecode.order.mapper.OrderMapper;
import com.codedecode.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

@Service
@Validated
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    RestTemplate restTemplate;

    @Value("${services.user.base-url}")
    String userServiceUrl;


    public OrderDTO saveOrderInDd(OrderDTOFrontE orderDTOFrontE) {
        UserDTO userDTO = fetchUserDetailsFromUserService(orderDTOFrontE.getUserId());
        Order order = new Order();
        order.setUserDTO(userDTO);
        order.setRestaurant(orderDTOFrontE.getRestaurant());
        order.setFoodItemsList(orderDTOFrontE.getFoodItemsList());
        order = orderRepo.save(order);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(order);

    }

    private UserDTO fetchUserDetailsFromUserService(Integer userId) {
           return restTemplate.getForObject(userServiceUrl+userId, UserDTO.class);
    }
}
