package com.sapo.ex7restfulapispring.service;


import java.io.Serializable;
import java.util.List;

public interface IGenericService<T extends Serializable> {
    List<T> getAllList();

    T findById(Integer id);

    void save(T t);

    List<T> paginationAndSort(int page);
}
