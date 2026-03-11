package com.gofore.springmodulithdemo.product;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> findProductsByIdSet(Set<UUID> ids);
    List<ProductDto> findFirstProducts(int limit);
    ProductDto createSafe(ProductDto product);
}

