package com.shopping.inventoryService.Service;

import com.shopping.inventoryService.DTO.InventoryRequest;
import com.shopping.inventoryService.DTO.InventoryResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryService {
    List<InventoryResponseDTO> checkInStock(List<String> skuCode);

    ResponseEntity<?> postInInventory(InventoryRequest inventoryRequest);

    ResponseEntity<?> deleteInventory(Long id);
}
