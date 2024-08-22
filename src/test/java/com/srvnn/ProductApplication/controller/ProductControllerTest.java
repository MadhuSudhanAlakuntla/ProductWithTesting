package com.srvnn.ProductApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srvnn.ProductApplication.entity.Product;
import com.srvnn.ProductApplication.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest
public class ProductControllerTest {
    @MockBean
    private ProductService productService;
    private MockMvc mockMvc;
    @MockBean
    private ObjectMapper objectMapper;

    @InjectMocks
    private ProductController underTest;


    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(99.99);
        when(productService.createProduct(product)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(99.99));
    }


    @Test
    public void testGetProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetProductById_throwsException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    public void testGetProductById() throws Exception {
        int id = 1;
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(99.99);

        when(productService.getProductById(anyLong())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Test Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("price").value(99.99));
    }
    @Test
    public void testGetProductById_secondApproach() {
        int id = 1;
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(99.99);

//        when(productService.getProductById(anyLong())).thenReturn(product);



    }

}
