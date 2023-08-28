package com.shopping.Order.Controller;

import com.shopping.Order.DTO.OrderRequestDTO;
import com.shopping.Order.Service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<ResponseEntity<?>> postOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return CompletableFuture.supplyAsync(()->this.orderService.postOrder(orderRequestDTO));
    }

    public CompletableFuture<ResponseEntity<?>> fallbackMethod(OrderRequestDTO orderRequestDTO, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Oops! Something went wrong"));

    }

}
