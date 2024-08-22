package com.srvnn.ProductApplication.controller;

import com.srvnn.ProductApplication.entity.Vehicle;
import com.srvnn.ProductApplication.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.yaml.snakeyaml.nodes.Tag.STR;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicleByAvailability(@RequestParam(required = false) Boolean isAvailable) {
            return new ResponseEntity<>(vehicleService.getAllVehicleByAvailability(isAvailable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createVehicle(@RequestBody List<Vehicle> vehicle) {
        try {
            vehicleService.createVehicle(vehicle);
            return ResponseEntity.ok("Vehicle created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Exception: %s", e.getMessage()));
        }
    }

}
