package com.srvnn.ProductApplication.service;


import com.srvnn.ProductApplication.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);
}
