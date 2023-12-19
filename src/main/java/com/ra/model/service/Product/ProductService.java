package com.ra.model.service.Product;

import com.ra.model.dto.product.ProductDTO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface ProductService extends IGenericService<Product,Integer> {
    List<Product> paginater(Integer noPage);
    int saveProductId(ProductDTO productDTO);
    Integer getTotalPage();
    void deleteProduct(int id);
    Boolean checkNameProduct(String name);
    void update(Product product);
    List<Product> findByCategoryId(Integer categoryId);
    List<Product> findByCategoryIdAndStatus(Integer categoryId,boolean status);

}
