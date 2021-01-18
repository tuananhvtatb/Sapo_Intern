package com.sapo.ex7restfulapispring.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_category")
public class Category extends BaseEntity {
    @Column(name = "code_category", unique = true)
    private String idCategory;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
