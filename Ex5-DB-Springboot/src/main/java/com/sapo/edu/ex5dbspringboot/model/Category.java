package com.sapo.edu.ex5dbspringboot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends BaseModel {

    private String codeCategory;
    private String name;
    private String description;

}
