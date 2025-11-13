package com.gofore.springmodulithdemo.inventory.api;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface InventoryService {
    List<StorageLocationDTO> findFirst(int count);
    List<StorageLocationDTO> findStorageLocationsByIdSet(Set<UUID> ids);

    StorageLocationDTO createStorageLocation(StorageLocationDTO storageLocationDTO);
}


