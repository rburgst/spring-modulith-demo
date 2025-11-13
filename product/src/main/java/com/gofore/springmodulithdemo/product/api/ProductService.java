package com.gofore.springmodulithdemo.product.api;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProductService {
    List<ProductDTO> findProductsByIdSet(Set<UUID> ids);
    List<ProductDTO> findFirstProducts(int limit);
    ProductDTO createSafe(ProductDTO product);
}

