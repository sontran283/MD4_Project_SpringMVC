package com.ra.model.service.Product;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface ProductService extends IGenericService<Product,Integer> {
    List<Category> paginater(Integer noPage);
    Integer getTotalPage();
}
