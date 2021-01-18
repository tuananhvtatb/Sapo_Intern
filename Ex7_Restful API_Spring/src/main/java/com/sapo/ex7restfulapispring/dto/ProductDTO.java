package com.sapo.ex7restfulapispring.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDTO extends BaseDTO {
    @NotBlank
    @Max(value = 10)
    private String codeProduct;

    @Positive
    private Integer categoryID;

    @Positive
    private Integer inventoryID;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    private String pathImage;

    @NotNull
    private Integer amount;

    private Integer amountSell;
}
