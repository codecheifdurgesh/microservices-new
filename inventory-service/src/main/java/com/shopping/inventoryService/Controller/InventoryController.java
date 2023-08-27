package com.shopping.inventoryService.Controller;

import com.shopping.inventoryService.DTO.InventoryRequest;
import com.shopping.inventoryService.DTO.InventoryResponseDTO;
import com.shopping.inventoryService.Entity.Inventory;
import com.shopping.inventoryService.Service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @GetMapping()
    public ResponseEntity<List<InventoryResponseDTO>> checkInStock(@RequestParam List<String> skuCode){
        List<InventoryResponseDTO> response = this.inventoryService.checkInStock(skuCode);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<?> postInInventory(@RequestBody InventoryRequest inventoryRequest){
        return this.inventoryService.postInInventory(inventoryRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long id){
        return this.inventoryService.deleteInventory(id);
    }

}
