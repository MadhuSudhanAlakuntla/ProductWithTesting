package com.srvnn.ProductApplication.repository;

import com.srvnn.ProductApplication.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    List<Vehicle> findByIsAvailable(Boolean available);
}
