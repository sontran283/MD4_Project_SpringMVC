package com.ra.model.service.Category;

import com.ra.model.dao.Category.CategoryDAO;
import com.ra.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryDAO.findByName(name);
    }

    @Override
    public List<Category> sortByName() {
        return categoryDAO.sortByName();
    }

    @Override
    public Category findById(Integer integer) {
        return categoryDAO.findById(integer);
    }

    @Override
    public boolean saveOrUpDate(Category category) {
        return categoryDAO.saveOrUpDate(category);
    }

    @Override
    public void delete(Integer integer) {
        categoryDAO.delete(integer);
    }
}
