package com.srvnn.ProductApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private UUID uuid;
    private String fullName;
    private int totalRides;
    private LocalDateTime dateTime;
}
