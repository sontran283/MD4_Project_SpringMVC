package com.ra.model.dao;

import java.util.List;

public interface IGenericDAO<T, ID> {
    List<T> findAll();

    List<T> findByName(String name);

    List<T> sortByName();

    T findById(ID id);

    boolean saveOrUpDate(T t);

    void delete(ID id);
}
