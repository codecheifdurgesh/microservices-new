package com.shopping.Order.Service;

import com.shopping.Order.DTO.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    public ResponseEntity<?> postOrder(OrderRequestDTO orderRequestDTO);

}
