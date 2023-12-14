package com.ra.model.service.Product;

import com.ra.model.dao.Product.ProductDAO;
import com.ra.model.dto.product.ProductDTO;
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
    public int saveProductId(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductPrice(productDTO.getProductPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setProductStatus(productDTO.getProductStatus());
        product.setCategory(productDTO.getCategory());
        product.setImg(productDTO.getFile().getOriginalFilename());
        return productDAO.saveProductId(product);
    }

    @Override
    public Integer getTotalPage() {
        return productDAO.getTotalPage();
    }

    @Override
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    @Override
    public Boolean checkNameProduct(String name) {
        return productDAO.checkNameProduct(name);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }
}
