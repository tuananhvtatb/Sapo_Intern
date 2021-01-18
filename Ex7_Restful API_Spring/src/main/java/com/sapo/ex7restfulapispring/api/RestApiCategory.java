package com.sapo.ex7restfulapispring.api;

import com.sapo.ex7restfulapispring.dto.CategoryDTO;
import com.sapo.ex7restfulapispring.service.impl.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/categories")
public class RestApiCategory {
    private final CategoryService categoryService;

    public RestApiCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // add category

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getList(@RequestParam(value = "page", required = false) Integer page){
        List<CategoryDTO> listContact;
        if(page > 0 ){
            listContact = categoryService.paginationAndSort(page);
        }else{
            listContact = categoryService.getAllList();
        }
        if (listContact.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listContact, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<CategoryService> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setCreatedDate(LocalDateTime.now());
        categoryService.save(categoryDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryDTO.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // update a category
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO cateOld = categoryService.findById(id);
        cateOld.setCodeCategory(categoryDTO.getCodeCategory());
        cateOld.setName(categoryDTO.getName());
        cateOld.setDescription(categoryDTO.getDescription());
        cateOld.setUpdatedDate(LocalDateTime.now());
        categoryService.save(cateOld);
        return ResponseEntity.ok().body(cateOld);
    }

    // tim theo ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") Integer id) {
        Optional<CategoryDTO> categoryDTO = Optional.ofNullable(categoryService.findById(id));
        return categoryDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
