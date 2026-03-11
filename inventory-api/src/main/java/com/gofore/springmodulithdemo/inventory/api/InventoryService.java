package com.gofore.springmodulithdemo.inventory.api;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface InventoryService {
    List<StorageLocationDto> findFirst(int count);
    List<StorageLocationDto> findStorageLocationsByIdSet(Set<UUID> ids);

    StorageLocationDto createStorageLocation(StorageLocationDto storageLocationDTO);
}


