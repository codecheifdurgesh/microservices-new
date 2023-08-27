package com.shopping.inventoryService.Repository;

import com.shopping.inventoryService.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);

    boolean existsBySkuCode(String skuCode);
}
