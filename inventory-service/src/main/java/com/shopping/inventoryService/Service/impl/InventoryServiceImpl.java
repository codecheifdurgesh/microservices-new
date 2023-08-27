package com.shopping.inventoryService.Service.impl;

import com.shopping.inventoryService.DTO.InventoryRequest;
import com.shopping.inventoryService.DTO.InventoryResponseDTO;
import com.shopping.inventoryService.Entity.Inventory;
import com.shopping.inventoryService.Repository.InventoryRepository;
import com.shopping.inventoryService.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<InventoryResponseDTO> checkInStock(List<String> skuCode) {
        return this.inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponseDTO.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity()>0)
                            .build()
                ).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> postInInventory(InventoryRequest inventoryRequest) {
        Inventory inventory=Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
        try{
            Inventory inventory1=this.inventoryRepository.save(inventory);
            return ResponseEntity.ok().body(inventory1);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<?> deleteInventory(Long id) {
        if(this.inventoryRepository.existsById(id)){
            this.inventoryRepository.deleteById(id);
            return ResponseEntity.ok().body("Deleted Successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
