package com.sapo.edu.ex9.model;


import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class ProductModel {

    @Id
    private Integer id;

    private Integer idProduct;

    private Integer amountSell;

    private LocalDateTime dateExpress;

    public ProductModel(){

    }

    public ProductModel(Integer id, Integer idProduct, Integer amountSell, LocalDateTime now) {
        this.id = id;
        this.idProduct = idProduct;
        this.amountSell = amountSell;
        this.dateExpress = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(Integer amountSell) {
        this.amountSell = amountSell;
    }

    public LocalDateTime getDateExpress() {
        return dateExpress;
    }

    public void setDateExpress(LocalDateTime dateExpress) {
        this.dateExpress = dateExpress;
    }
}
