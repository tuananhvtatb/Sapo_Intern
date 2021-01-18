package com.sapo.ex7restfulapispring.service.impl;

import com.sapo.ex7restfulapispring.config.Constants;
import com.sapo.ex7restfulapispring.repository.CategoryRepository;
import com.sapo.ex7restfulapispring.repository.ProductRepository;
import com.sapo.ex7restfulapispring.dto.CategoryDTO;
import com.sapo.ex7restfulapispring.entities.Category;
import com.sapo.ex7restfulapispring.entities.Product;
import com.sapo.ex7restfulapispring.mapper.CategoryMapper;
import com.sapo.ex7restfulapispring.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements IGenericService<CategoryDTO> {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ProductRepository productRepository1) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository1;
    }


    @Override
    public List<CategoryDTO> getAllList() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category cate : categoryRepository.findAll()
        ) {
            categoryDTOS.add(categoryMapper.convertEntityToDTO(cate));
        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO findById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        return categoryMapper.convertEntityToDTO(category);
    }

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.covertDtoToEntity(categoryDTO);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> paginationAndSort(int page) {
        PageRequest pageReq = PageRequest.of(page, Constants.ITEM_ON_PAGE, Sort.Direction.ASC, "name");
        Page<Category> pageCate = categoryRepository.findAll(pageReq);
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category cate : pageCate.getContent()
        ) {
            categoryDTOS.add(categoryMapper.convertEntityToDTO(cate));
        }
        return categoryDTOS;
    }

    @Transactional
    public void delete(Integer id) {
        Category category = categoryRepository.getOne(id);
        List<Product> products = category.getProducts();
        if (!products.isEmpty()) {
            for (Product pro : products
            ) {
                productRepository.delete(pro);
            }
        }

        categoryRepository.deleteById(id);
    }
}
