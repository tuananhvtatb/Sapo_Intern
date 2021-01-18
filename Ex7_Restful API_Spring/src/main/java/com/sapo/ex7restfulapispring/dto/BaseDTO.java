package com.sapo.ex7restfulapispring.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data

public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
