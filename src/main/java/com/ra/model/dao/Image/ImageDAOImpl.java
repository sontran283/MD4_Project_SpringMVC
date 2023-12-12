package com.ra.model.dao.Image;

import com.ra.model.entity.Category;
import com.ra.model.entity.Image;
import com.ra.util.ConnectionDataBase;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageDAOImpl implements ImageDAO {
    private int LIMIT = 4;
    private int totalPage = 0;

    @Override
    public List<Image> findAll() {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Image> imageList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CATEGORY_FIND_ALL()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Image image = new Image();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return imageList;
    }

    @Override
    public List<Image> findByName(String name) {
       return null;
    }

    @Override
    public List<Image> sortByName() {
       return null;
    }

    @Override
    public Image findById(Integer integer) {
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
        return null;
    }

    @Override
    public boolean saveOrUpDate(Image image) {
      return false;
    }

    @Override
    public void delete(Integer id) {
    }


    @Override
    public List<Image> findByProductId(Integer id) {
        return null;
    }

    @Override
    public List<Image> paginater(Integer noPage) {
       return null;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }

    @Override
    public boolean addImage(Image image, Integer productId) {
        Connection connection=ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement=connection.prepareCall("CALL IMAGE_ADD(?,?)");
            callableStatement.setString(1,image.getImgUrl());
            callableStatement.setInt(2,productId);
            int check=callableStatement.executeUpdate();
            if (check>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return false;
    }
}
