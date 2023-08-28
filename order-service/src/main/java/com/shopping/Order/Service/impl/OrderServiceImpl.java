package com.shopping.Order.Service.impl;

import com.shopping.Order.DTO.InventoryResponseDTO;
import com.shopping.Order.DTO.OrderRequestDTO;
import com.shopping.Order.Entity.Order;
import com.shopping.Order.Entity.OrderLineItems;
import com.shopping.Order.Repository.OrderRepository;
import com.shopping.Order.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private final WebClient.Builder webClientBuilder;

    @Override
    public ResponseEntity<?> postOrder(OrderRequestDTO orderRequestDTO) {
        Order order=new Order();
        String s= UUID.randomUUID().toString();
        order.setOrderNumber(s);
        System.out.println("Hello world");
        System.out.println(orderRequestDTO.getOrderLineItemsDTOS()+"$$$$$$$$$$$$$$$$$$$$$$$");
        List<OrderLineItems> orderLineItems=orderRequestDTO.getOrderLineItemsDTOS().stream().map(orderLineItemsDTO -> {
            return OrderLineItems.builder()
                    .price(orderLineItemsDTO.getPrice())
                    .skuCode(orderLineItemsDTO.getSkuCode())
                    .quantity(orderLineItemsDTO.getQuantity())
                    .build();
        }).collect(Collectors.toList());
        order.setOrderLineItems(orderLineItems);
        List<String> skuCodes= order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).collect(Collectors.toList());

        String inventoryServiceUri = "http://INVENTORY-SERVICE/inventory";
        URI uri = UriComponentsBuilder.fromUriString(inventoryServiceUri)
                .queryParam("skuCode", skuCodes)
                .build()
                .toUri();

        InventoryResponseDTO[] responseEntity = webClientBuilder.build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(InventoryResponseDTO[].class)
                .block();


        assert responseEntity != null;
        boolean allProductsinStock=Arrays.stream(responseEntity).allMatch(InventoryResponseDTO::isInStock);
        if(allProductsinStock){
            this.orderRepository.save(order);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Product does not exist in the stock");
    }
}
