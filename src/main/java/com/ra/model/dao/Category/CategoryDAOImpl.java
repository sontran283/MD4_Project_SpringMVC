package com.ra.model.dao.Category;

import com.ra.model.entity.Category;
import com.ra.util.ConnectionDataBase;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> findAll() {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Category> categoryList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CATEGORY_FIND_ALL()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setCategoryDescription(resultSet.getString("description"));
                category.setCategoryStatus(resultSet.getBoolean("status"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return categoryList;
    }

    @Override
    public List<Category> findByName(String name) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Category> categoryList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CATEGORY_SEARCH_BY_NAME(?)}");
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setCategoryDescription(resultSet.getString("description"));
                category.setCategoryStatus(resultSet.getBoolean("status"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return categoryList;
    }

    @Override
    public List<Category> sortByName() {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Category> categoryList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CATEGORY_SEARCH_BY_NAME()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setCategoryDescription(resultSet.getString("description"));
                category.setCategoryStatus(resultSet.getBoolean("status"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return categoryList;
    }

    @Override
    public Category findById(Integer integer) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        Category category = new Category();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CATEGORY_FIND_BY_ID(?)}");
            callableStatement.setInt(1, integer);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setCategoryDescription(resultSet.getString("description"));
                category.setCategoryStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return category;
    }

    @Override
    public boolean saveOrUpDate(Category category) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        try {
            if (category.getCategoryId() == 0) {
                CallableStatement callableStatement = connection.prepareCall("{CALL CATEGORY_ADD(?,?,?)}");
                callableStatement.setString(1, category.getCategoryName());
                callableStatement.setString(2, category.getCategoryDescription());
                callableStatement.setBoolean(3, category.getCategoryStatus());
                int check = callableStatement.executeUpdate();
                if (check > 0) {
                    return true;
                }
            } else {
                CallableStatement callableStatement = connection.prepareCall("{CALL CATEGORY_UPDATE(?,?,?,?)}");
                callableStatement.setInt(1, category.getCategoryId());
                callableStatement.setString(2, category.getCategoryName());
                callableStatement.setString(3, category.getCategoryDescription());
                callableStatement.setBoolean(4, category.getCategoryStatus());
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
    public void delete(Integer categoryId) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CATEGORY_DELETE(?)}");
            callableStatement.setInt(1, categoryId);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }
}
