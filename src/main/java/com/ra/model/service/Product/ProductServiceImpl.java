package com.ra.model.service.Product;

import com.ra.model.dao.Product.ProductDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return productDAO.findByName(name);
    }

    @Override
    public List<Product> sortByName() {
        return productDAO.sortByName();
    }

    @Override
    public Product findById(Integer integer) {
        return productDAO.findById(integer);
    }

    @Override
    public boolean saveOrUpDate(Product product) {
        return productDAO.saveOrUpDate(product);
    }

    @Override
    public void delete(Integer integer) {
        productDAO.delete(integer);
    }

    @Override
    public List<Product> paginater(Integer noPage) {
        return productDAO.paginater(noPage);
    }

    @Override
    public int saveProductId(Product product) {
        return productDAO.saveProductId(product);
    }

    @Override
    public Integer getTotalPage() {
        return productDAO.getTotalPage();
    }
}
