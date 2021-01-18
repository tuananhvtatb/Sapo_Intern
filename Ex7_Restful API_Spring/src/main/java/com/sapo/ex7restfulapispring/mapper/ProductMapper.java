package com.sapo.ex7restfulapispring.mapper;

import com.sapo.ex7restfulapispring.dto.ProductDTO;
import com.sapo.ex7restfulapispring.entities.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends BaseMapper<ProductDTO, Product> {

    public ProductMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public ProductDTO convertEntityToDTO(Product product) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(product, ProductDTO.class);
    }

    @Override
    public Product covertDtoToEntity(ProductDTO productDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(productDTO, Product.class);
    }

}
