package com.sapo.edu.ex9.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "amount_sell")
    private Integer amountSell;

    @Column(name = "date_express")
    private LocalDateTime dateExpress;

}
