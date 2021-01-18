package com.sapo.edu.ex9.service;

import com.sapo.edu.ex9.entity.ProductEntity;
import com.sapo.edu.ex9.model.Product;
import com.sapo.edu.ex9.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public void save(Product product){
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        productRepository.save(productEntity);
    }
}
