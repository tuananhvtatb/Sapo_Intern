package com.sapo.edu.ex5dbspringboot.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public abstract class BaseModel {
    protected Integer id;
    protected Timestamp createdDate;
    protected Timestamp updatedDate;
}
