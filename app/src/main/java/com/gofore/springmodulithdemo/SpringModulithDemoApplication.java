package com.gofore.springmodulithdemo;

import com.gofore.springmodulithdemo.inventory.api.InventoryService;
import com.gofore.springmodulithdemo.inventory.api.StorageLocationDTO;
import com.gofore.springmodulithdemo.product.api.ProductDTO;
import com.gofore.springmodulithdemo.product.api.ProductService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication(proxyBeanMethods = false)
public class SpringModulithDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringModulithDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(ProductService productService, InventoryService inventoryService) {
        return args -> {
            List<StorageLocationDTO> locations = inventoryService.findFirst(5);
            if (locations.isEmpty()) {
                final StorageLocationDTO created = inventoryService.createStorageLocation(new StorageLocationDTO(null, "Main Warehouse", "1234 Warehouse St."));
                locations = List.of(created);
            }
            StorageLocationDTO location = locations.getFirst();
            List<ProductDTO> products = productService.findFirstProducts(5);

            if (products.size() < 5) {
                for (int i = 0; i < 5; i++) {
                    productService.createSafe(new ProductDTO("Sample Product " + (products.size() + i), "This is a safe product", 200 + products.size() + i, location.getId()));
                }
            }
        };
    }
}




