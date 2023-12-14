package com.ra.model.dao.Product;

import com.ra.model.dao.Category.CategoryDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.util.ConnectionDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private int LIMIT = 4;
    private int totalPage = 0;

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Product> findAll() {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Product> productList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_FIND_ALL()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setImg(resultSet.getString("img"));
                product.setProductName(resultSet.getString("name"));
                product.setProductDescription(resultSet.getString("description"));
                product.setProductPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setProductStatus(resultSet.getBoolean("status"));
                Category category = categoryDAO.findById(resultSet.getInt("category_id"));
                product.setCategory(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return productList;
    }

    @Override
    public List<Product> findByName(String name) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Product> productList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_SEARCH_BY_NAME(?)}");
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                Category category = categoryDAO.findById(resultSet.getInt("category_id"));
                product.setCategory(category);
                product.setImg(resultSet.getString("img"));
                product.setProductName(resultSet.getString("name"));
                product.setProductDescription(resultSet.getString("description"));
                product.setProductPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setProductStatus(resultSet.getBoolean("status"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return productList;
    }

    @Override
    public List<Product> sortByName() {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Product> productList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_SORT_BY_NAME()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                Category category = categoryDAO.findById(resultSet.getInt("category_id"));
                product.setCategory(category);
                product.setImg(resultSet.getString("img"));
                product.setProductName(resultSet.getString("name"));
                product.setProductDescription(resultSet.getString("description"));
                product.setProductPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setProductStatus(resultSet.getBoolean("status"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return productList;
    }

    @Override
    public Product findById(Integer integer) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        Product product = new Product();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_FIND_BY_ID(?)}");
            callableStatement.setInt(1, integer);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                product.setProductId(resultSet.getInt("product_id"));
                Category category = categoryDAO.findById(resultSet.getInt("category_id"));
                product.setCategory(category);
                product.setImg(resultSet.getString("img"));
                product.setProductName(resultSet.getString("name"));
                product.setProductDescription(resultSet.getString("description"));
                product.setProductPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setProductStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return product;
    }

    @Override
    public boolean saveOrUpDate(Product product) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        try {
            if (product.getProductId() == 0) {
                CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_ADD(?,?,?,?,?,?,?)}");
                callableStatement.setInt(1, product.getCategory().getCategoryId());
                callableStatement.setString(2, product.getImg());
                callableStatement.setString(3, product.getProductName());
                callableStatement.setString(4, product.getProductDescription());
                callableStatement.setDouble(5, product.getProductPrice());
                callableStatement.setInt(6, product.getQuantity());
                callableStatement.setBoolean(7, product.getProductStatus());
                int check = callableStatement.executeUpdate();
                if (check > 0) {
                    return true;
                }
            } else {
                CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_UPDATE(?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1, product.getProductId());
                callableStatement.setInt(2, product.getCategory().getCategoryId());
                callableStatement.setString(3, product.getImg());
                callableStatement.setString(4, product.getProductName());
                callableStatement.setString(5, product.getProductDescription());
                callableStatement.setDouble(6, product.getProductPrice());
                callableStatement.setInt(7, product.getQuantity());
                callableStatement.setBoolean(8, product.getProductStatus());
                int check = callableStatement.executeUpdate();
                if (check > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return false;
    }

    @Override
    public void update(Product product) {
        CallableStatement callableStatement = null;
        Connection connection = ConnectionDataBase.openConnection();
        try {
            callableStatement = connection.prepareCall("{CALL PRODUCT_UPDATE(?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, product.getProductId());
            callableStatement.setInt(2, product.getCategory().getCategoryId());
            callableStatement.setString(3, product.getImg());
            callableStatement.setString(4, product.getProductName());
            callableStatement.setString(5, product.getProductDescription());
            callableStatement.setDouble(6, product.getProductPrice());
            callableStatement.setInt(7, product.getQuantity());
            callableStatement.setBoolean(8, product.getProductStatus());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_UPDATE_STATUS(?)}");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }

    @Override
    public List<Product> paginater(Integer noPage) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Product> productList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_PAGINATION(?,?,?)}");
            callableStatement.setInt(1, LIMIT);
            callableStatement.setInt(2, noPage);
            callableStatement.setInt(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                Category category = categoryDAO.findById(resultSet.getInt("category_id"));
                product.setCategory(category);
                product.setImg(resultSet.getString("img"));
                product.setProductName(resultSet.getString("name"));
                product.setProductDescription(resultSet.getString("description"));
                product.setProductPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setProductStatus(resultSet.getBoolean("status"));
                productList.add(product);
            }
            this.totalPage = callableStatement.getInt(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return productList;
    }

    @Override
    public int saveProductId(Product product) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_ADD_PRODUCT_ID(?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, product.getCategory().getCategoryId());
            callableStatement.setString(2, product.getImg());
            callableStatement.setString(3, product.getProductName());
            callableStatement.setString(4, product.getProductDescription());
            callableStatement.setDouble(5, product.getProductPrice());
            callableStatement.setInt(6, product.getQuantity());
            callableStatement.setBoolean(7, product.getProductStatus());
            callableStatement.registerOutParameter(8, Types.INTEGER);
            callableStatement.executeUpdate();
            return callableStatement.getInt(8);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }

    @Override
    public void deleteProduct(int id) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PRODUCT_DELETE(?)}");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }

    @Override
    public Boolean checkNameProduct(String name) {
        Connection connection = ConnectionDataBase.openConnection();
        CallableStatement callableStatement = null;
        try {
            CallableStatement callableStatement1 = connection.prepareCall("{CALL PRODUCT_CHECK_NAME(?)}");
            callableStatement1.setString(1, name);
            ResultSet resultSet = callableStatement1.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }
}
