package com.shopping.Order.Controller;

import com.shopping.Order.DTO.OrderRequestDTO;
import com.shopping.Order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<?> postOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return this.orderService.postOrder(orderRequestDTO);
    }
}
