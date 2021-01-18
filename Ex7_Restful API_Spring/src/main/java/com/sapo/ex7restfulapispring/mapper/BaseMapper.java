package com.sapo.ex7restfulapispring.mapper;

import org.modelmapper.ModelMapper;


public abstract class BaseMapper<T, E> {

    public final ModelMapper modelMapper;

    protected BaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public abstract T convertEntityToDTO(E e);

    public abstract E covertDtoToEntity(T t);

}
