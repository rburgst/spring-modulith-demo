package com.gofore.springmodulithdemo.product.impl;

import com.gofore.springmodulithdemo.notification.api.NotificationDTO;
import com.gofore.springmodulithdemo.product.api.ProductDTO;
import com.gofore.springmodulithdemo.product.api.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ApplicationEventPublisher events;
    private final ProductRepository repository;

    public ProductServiceImpl(ApplicationEventPublisher events, ProductRepository repository) {
        this.events = events;
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<ProductDTO> findProductsByIdSet(Set<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return mapToDtoList(repository.findByIdIn(ids));
    }

    @Override
    @Transactional
    public List<ProductDTO> findFirstProducts(int limit) {
        if (limit <= 0) {
            return List.of();
        }
        return mapToDtoList(repository.findAll(PageRequest.of(0, limit)).getContent());
    }


    @Override
    @Transactional
    public ProductDTO createSafe(ProductDTO product) {
        ProductEntity saved = repository.save(new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStorageLocationId()
        ));
        events.publishEvent(new NotificationDTO(new Date(), "SMS", product.getName()));
        return mapToDto(saved);
    }


    private ProductDTO mapToDto(ProductEntity entity) {
        return new ProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStorageLocationId()
        );
    }

    private List<ProductDTO> mapToDtoList(List<ProductEntity> products) {
        return products.stream()
                .map(this::mapToDto)
                .toList();
    }
}

