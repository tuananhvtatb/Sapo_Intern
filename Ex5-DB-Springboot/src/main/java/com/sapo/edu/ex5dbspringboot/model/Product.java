package com.sapo.edu.ex5dbspringboot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends BaseModel {
    private String codeProduct;
    private Integer categoryID;
    private Integer repositoryID;
    private String name;
    private String description;
    private String pathName;
    private Integer amount;
    private Integer amountSell;

}
