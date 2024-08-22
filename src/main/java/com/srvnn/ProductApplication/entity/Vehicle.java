package com.srvnn.ProductApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Table(name = "vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {
    @Id
    private UUID uuid = UUID.randomUUID();
    private String model;
    @Column(name = "is_available")
    private Boolean isAvailable;
}
