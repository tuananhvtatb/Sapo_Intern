package com.sapo.ex7restfulapispring.api;

import com.sapo.ex7restfulapispring.dto.ProductDTO;
import com.sapo.ex7restfulapispring.service.impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/products")
public class RestApiProduct {

    private final ProductService productService;

    public RestApiProduct(ProductService productService) {
        this.productService = productService;
    }

    // Phân trang và sắp xếp theo tên
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> listAllContact(@PathParam("page") int page) {
        List<ProductDTO> listContact = productService.paginationAndSort(page);
        if (listContact.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listContact, HttpStatus.OK);
    }

    // add a new product
    @PostMapping(value = "")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        productService.save(productDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productDTO.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // update product with id
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO productOld = productService.findById(id);
        productDTO.setId(productOld.getId());
        productService.save(productDTO);
        return ResponseEntity.ok().body(productDTO);
    }

    // tim theo ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("id") Integer id) {
        ProductDTO prd = productService.findById(id);
        return ResponseEntity.ok().body(prd);
    }

}
