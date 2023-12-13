package com.ra.model.dao.Product;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductDAO extends IGenericDAO<Product, Integer> {
    List<Product> paginater(Integer noPage);
    int saveProductId(Product product);
    Integer getTotalPage();
    void deleteProduct(int id);
    Boolean checkNameProduct(String name);
}
