package com.gofore.springmodulithdemo.app;

import com.gofore.springmodulithdemo.inventory.api.StorageLocationDTO;
import com.gofore.springmodulithdemo.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithLocationDTO {
    private ProductDTO product;
    private StorageLocationDTO storageLocation;
}

