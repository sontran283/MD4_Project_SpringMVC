package com.ra.model.dao.Category;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;

import java.util.List;

public interface CategoryDAO extends IGenericDAO<Category, Integer> {
  List<Category> paginater(Integer noPage);
  Integer getTotalPage();
}
