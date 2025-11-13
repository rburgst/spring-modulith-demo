package com.gofore.springmodulithdemo.inventory.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageLocationDTO {
    private UUID id;
    private String name;
    private String address;
}

