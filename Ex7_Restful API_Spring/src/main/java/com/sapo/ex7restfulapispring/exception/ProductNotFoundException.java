package com.sapo.ex7restfulapispring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ResponseStatus()
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ProductNotFoundException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Resource Not Found",
                details);

        return ResponseEntityBuilder.build(err);
    }
}
