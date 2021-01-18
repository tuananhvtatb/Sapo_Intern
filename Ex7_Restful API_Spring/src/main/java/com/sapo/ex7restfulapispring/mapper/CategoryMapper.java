package com.sapo.ex7restfulapispring.mapper;

import com.sapo.ex7restfulapispring.dto.CategoryDTO;
import com.sapo.ex7restfulapispring.entities.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends BaseMapper<CategoryDTO, Category> {

    protected CategoryMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public CategoryDTO convertEntityToDTO(Category category) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(category, CategoryDTO.class);
    }

    @Override
    public Category covertDtoToEntity(CategoryDTO categoryDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(categoryDTO, Category.class);
    }
}
