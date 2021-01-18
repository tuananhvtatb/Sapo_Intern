package com.sapo.ex7restfulapispring.service.impl;

import com.sapo.ex7restfulapispring.config.Constants;
import com.sapo.ex7restfulapispring.repository.ProductRepository;
import com.sapo.ex7restfulapispring.dto.ProductDTO;
import com.sapo.ex7restfulapispring.entities.Product;
import com.sapo.ex7restfulapispring.mapper.ProductMapper;
import com.sapo.ex7restfulapispring.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService implements IGenericService<ProductDTO> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public List<ProductDTO> getAllList() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product pro : productRepository.findAll()
        ) {
            productDTOList.add(productMapper.convertEntityToDTO(pro));
        }
        return productDTOList;
    }

    @Override
    public ProductDTO findById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productMapper.convertEntityToDTO(product);
    }

    @Override
    public void save(ProductDTO productDTO) {
        Product product = productMapper.covertDtoToEntity(productDTO);
        productRepository.save(product);
    }


    @Override
    public List<ProductDTO> paginationAndSort(int page) {
        PageRequest pageReq = PageRequest.of(page, Constants.ITEM_ON_PAGE, Sort.Direction.ASC, "name");
        Page<Product> pageProduct = productRepository.findAll(pageReq);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product pro : pageProduct.getContent()
        ) {
            productDTOList.add(productMapper.convertEntityToDTO(pro));
        }
        return productDTOList;
    }


}
