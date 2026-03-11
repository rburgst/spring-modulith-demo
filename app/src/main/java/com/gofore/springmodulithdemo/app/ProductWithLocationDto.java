package com.gofore.springmodulithdemo.app;

import com.gofore.springmodulithdemo.inventory.api.StorageLocationDto;
import com.gofore.springmodulithdemo.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithLocationDto {
    private ProductDto product;
    private StorageLocationDto storageLocation;
}

