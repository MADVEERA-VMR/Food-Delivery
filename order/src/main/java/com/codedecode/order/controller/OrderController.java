package com.codedecode.order.controller;

import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFrontE;
import com.codedecode.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDTO> placeOrder(@Valid @RequestBody OrderDTOFrontE orderDTOFrontE) {
        OrderDTO orderDTO = orderService.saveOrderInDd(orderDTOFrontE);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }
}
