package com.srvnn.ProductApplication.controller;

import com.srvnn.ProductApplication.entity.Vehicle;
import com.srvnn.ProductApplication.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Test
    void test_GetAllUnAvailableVehicles() throws Exception {
        List<Vehicle> vechileList = List.of(
                new Vehicle(UUID.randomUUID(), "Car", false),
                new Vehicle(UUID.randomUUID(), "Truck", false)
        );

        when(vehicleService.getAllVehicleByAvailability(false)).thenReturn(vechileList);

        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles").param("isAvailable","false"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].model").value(vechileList.get(0).getModel()))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].model").value(vechileList.get(1).getModel()));
    }
}
