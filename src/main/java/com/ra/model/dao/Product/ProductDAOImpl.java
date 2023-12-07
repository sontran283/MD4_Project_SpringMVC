package com.ra.model.dao.Product;

import com.ra.model.dao.Category.CategoryDAO;
import com.ra.model.dao.Category.CategoryDAOImpl;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.util.ConnectionDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Product> findAll() {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = ConnectionDataBase.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_FIND_ALL()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
//                Product product = new Product();
//                product.setProductId(resultSet.getInt("id"));
//                product.setProductName(resultSet.getString("name"));
//                product.setPrice(resultSet.getDouble("price"));
//                Category category = categoryDAO.findById(resultSet.getInt("category_id"));
//                product.setCategory(category);
//                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return products;
    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }

    @Override
    public List<Product> sortByName() {
        return null;
    }

    @Override
    public Product findById(Integer integer) {
        return null;
    }

    @Override
    public boolean saveOrUpDate(Product product) {
        return false;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_DELETE(?)}");
            callableStatement.setInt(1, integer);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
