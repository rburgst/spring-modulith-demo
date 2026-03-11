package com.gofore.springmodulithdemo.product.impl;

import com.gofore.springmodulithdemo.product.ProductCreated;
import com.gofore.springmodulithdemo.product.ProductDto;
import com.gofore.springmodulithdemo.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ApplicationEventPublisher events;
    private final ProductRepository repository;
//    private final NotificationService notificationService;

    @Override
    @Transactional
    public List<ProductDto> findProductsByIdSet(Set<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return mapToDtoList(repository.findByIdIn(ids));
    }

    @Override
    @Transactional
    public List<ProductDto> findFirstProducts(int limit) {
        if (limit <= 0) {
            return List.of();
        }
        return mapToDtoList(repository.findAll(PageRequest.of(0, limit)).getContent());
    }


    @Override
    @Transactional
    public ProductDto createSafe(ProductDto product) {
        ProductEntity saved = repository.save(new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStorageLocationId()
        ));
        events.publishEvent(new ProductCreated(product.getName(), new Date()));
//        notificationService.createNotification(new NotificationDTO(new Date(), "SMS", product.getName()));
        return mapToDto(saved);
    }


    private ProductDto mapToDto(ProductEntity entity) {
        return new ProductDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStorageLocationId()
        );
    }

    private List<ProductDto> mapToDtoList(List<ProductEntity> products) {
        return products.stream()
                .map(this::mapToDto)
                .toList();
    }
}


