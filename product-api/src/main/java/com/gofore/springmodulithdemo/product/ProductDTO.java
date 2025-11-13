package com.gofore.springmodulithdemo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private String description;
    private int price;
    private UUID storageLocationId;

    public ProductDTO(String name, String description, int price, UUID storageLocationId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.storageLocationId = storageLocationId;
    }
}

