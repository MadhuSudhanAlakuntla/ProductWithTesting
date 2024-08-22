package com.srvnn.ProductApplication.service;

import com.srvnn.ProductApplication.entity.Vehicle;
import com.srvnn.ProductApplication.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicleByAvailability(Boolean isAvailable) {
        return vehicleRepository.findByIsAvailable(isAvailable);
    }

    public void createVehicle(List<Vehicle> vehicle) {
        vehicleRepository.saveAll(vehicle);
    }
}
