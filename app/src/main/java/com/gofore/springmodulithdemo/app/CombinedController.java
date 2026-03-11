package com.gofore.springmodulithdemo.app;

import com.gofore.springmodulithdemo.inventory.api.InventoryService;
import com.gofore.springmodulithdemo.inventory.api.StorageLocationDto;
import com.gofore.springmodulithdemo.product.ProductDto;
import com.gofore.springmodulithdemo.product.ProductService;
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
    public List<ProductWithLocationDto> getProductsWithLocations(@RequestParam Set<UUID> ids) {
        List<ProductDto> products = productService.findProductsByIdSet(ids);
        return combineProductsWithLocations(products);
    }

    @GetMapping("/products")
    public List<ProductWithLocationDto> findFirstProducts(@RequestParam(defaultValue = "10") int limit) {
        List<ProductDto> products = productService.findFirstProducts(limit);
        return combineProductsWithLocations(products);
    }

    private List<ProductWithLocationDto> combineProductsWithLocations(List<ProductDto> products) {
        Map<UUID, StorageLocationDto> locationMap = getStorageLocationsMap(products);
        
        return products.stream()
                .map(product -> new ProductWithLocationDto(
                        product,
                        product.getStorageLocationId() != null 
                                ? locationMap.get(product.getStorageLocationId()) 
                                : null
                ))
                .collect(Collectors.toList());
    }

    private Map<UUID, StorageLocationDto> getStorageLocationsMap(List<ProductDto> products) {
        // Extract storage location IDs from products
        Set<UUID> storageLocationIds = products.stream()
                .map(ProductDto::getStorageLocationId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        
        // Get storage locations by IDs
        List<StorageLocationDto> storageLocations = inventoryService.findStorageLocationsByIdSet(storageLocationIds);
        
        // Create a map of location ID to location for easy lookup
        return storageLocations.stream()
                .collect(Collectors.toMap(StorageLocationDto::getId, location -> location));
    }
}


