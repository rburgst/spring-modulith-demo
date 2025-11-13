package com.gofore.springmodulithdemo;

import com.gofore.springmodulithdemo.inventory.api.InventoryService;
import com.gofore.springmodulithdemo.inventory.api.StorageLocationDTO;
import com.gofore.springmodulithdemo.product.api.ProductDTO;
import com.gofore.springmodulithdemo.product.api.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CombinedController {

    private final ProductService productService;
    private final InventoryService inventoryService;

    public CombinedController(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/products-with-locations")
    public List<ProductWithLocationDTO> getProductsWithLocations(@RequestParam Set<UUID> ids) {
        List<ProductDTO> products = productService.findProductsByIdSet(ids);
        return combineProductsWithLocations(products);
    }

    @GetMapping("/products")
    public List<ProductWithLocationDTO> findFirstProducts(@RequestParam(defaultValue = "10") int limit) {
        List<ProductDTO> products = productService.findFirstProducts(limit);
        return combineProductsWithLocations(products);
    }

    private List<ProductWithLocationDTO> combineProductsWithLocations(List<ProductDTO> products) {
        Map<UUID, StorageLocationDTO> locationMap = getStorageLocationsMap(products);
        
        return products.stream()
                .map(product -> new ProductWithLocationDTO(
                        product,
                        product.getStorageLocationId() != null 
                                ? locationMap.get(product.getStorageLocationId()) 
                                : null
                ))
                .collect(Collectors.toList());
    }

    private Map<UUID, StorageLocationDTO> getStorageLocationsMap(List<ProductDTO> products) {
        // Extract storage location IDs from products
        Set<UUID> storageLocationIds = products.stream()
                .map(ProductDTO::getStorageLocationId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        
        // Get storage locations by IDs
        List<StorageLocationDTO> storageLocations = inventoryService.findStorageLocationsByIdSet(storageLocationIds);
        
        // Create a map of location ID to location for easy lookup
        return storageLocations.stream()
                .collect(Collectors.toMap(StorageLocationDTO::getId, location -> location));
    }
}


