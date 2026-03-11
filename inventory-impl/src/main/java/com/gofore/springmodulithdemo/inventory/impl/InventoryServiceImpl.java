package com.gofore.springmodulithdemo.inventory.impl;

import com.gofore.springmodulithdemo.inventory.api.InventoryService;
import com.gofore.springmodulithdemo.inventory.api.StorageLocationDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final StorageLocationRepository repository;

    public InventoryServiceImpl(StorageLocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StorageLocationDto> findFirst(int count) {
        return repository.findAll(Pageable.ofSize(count)).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<StorageLocationDto> findStorageLocationsByIdSet(Set<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return repository.findByIdIn(ids).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StorageLocationDto createStorageLocation(StorageLocationDto storageLocationDTO) {
        StorageLocationEntity entity = new StorageLocationEntity();
        entity.setName(storageLocationDTO.getName());
        entity.setAddress(storageLocationDTO.getAddress());
        StorageLocationEntity savedEntity = repository.save(entity);
        return mapToDTO(savedEntity);
    }

    private StorageLocationDto mapToDTO(StorageLocationEntity entity) {
        return new StorageLocationDto(entity.getId(), entity.getName(), entity.getAddress());
    }
}


