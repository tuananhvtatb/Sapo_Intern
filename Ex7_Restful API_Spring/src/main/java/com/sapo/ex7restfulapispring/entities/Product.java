package com.sapo.ex7restfulapispring.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tbl_product")
public class Product extends BaseEntity {
    @NotBlank(message = "Valid")
    @Column(name = "code_product", nullable = false, unique = true, length = 10)
    private String codeProduct;


    @Size(max = 200)
    @NotNull
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", length = 3000)
    private String description;

    @NotNull
    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "path_image")
    private String pathImage;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "amount_sell")
    private Integer amountSell;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name ="inventory_id")
    private Integer inventoryID;
}
