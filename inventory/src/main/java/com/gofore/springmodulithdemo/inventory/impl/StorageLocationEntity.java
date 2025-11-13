package com.gofore.springmodulithdemo.inventory.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "storage_locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;
    private String address;
}

