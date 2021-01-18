package com.sapo.ex7restfulapispring.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends BaseDTO {
    @NotBlank
    @Max(value = 10)
    private String codeCategory;

    @NotNull
    private String name;

    private String description;
}