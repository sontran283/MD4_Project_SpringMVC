package com.ra.model.dao.OrderDetail;

import com.ra.model.entity.OrderDetail;
import com.ra.util.ConnectionDataBase;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class OrderDetailDAOImpl  implements  OrderDetailDAO{
    @Override
    public boolean save(OrderDetail orderDetail) {
        Connection connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_DETAIL_ADD(?,?,?,?)}");
            callableStatement.setInt(1, orderDetail.getOrder().getOrder_id());
            callableStatement.setInt(2, orderDetail.getProduct().getProductId());
            callableStatement.setInt(3, orderDetail.getQuantity());
            callableStatement.setDouble(4, orderDetail.getPrice());
            int check= callableStatement.executeUpdate();
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
