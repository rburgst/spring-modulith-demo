package com.gofore.springmodulithdemo.inventory.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface StorageLocationRepository extends JpaRepository<StorageLocationEntity, UUID> {
    List<StorageLocationEntity> findByIdIn(Set<UUID> ids);
}


