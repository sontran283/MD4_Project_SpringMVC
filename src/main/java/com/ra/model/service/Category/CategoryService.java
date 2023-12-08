package com.ra.model.service.Category;

import com.ra.model.entity.Category;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface CategoryService extends IGenericService<Category,Integer> {
    List<Category> paginater(Integer noPage);
    Integer getTotalPage();
}
