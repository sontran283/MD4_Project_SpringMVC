package com.ra.model.service;

import com.ra.model.dao.IGenericDAO;

import java.util.List;

public interface IGenericService<T,ID> {
    List<T> findAll();

    List<T> findByName(String name);

    List<T> sortByName();

    T findById(ID id);

    boolean saveOrUpDate(T t);

    void delete(ID id);
}
