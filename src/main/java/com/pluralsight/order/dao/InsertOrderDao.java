package com.pluralsight.order.dao;

import com.pluralsight.order.dto.OrderDto;
import com.pluralsight.order.dto.OrderDetailDto;
import com.pluralsight.order.util.ExceptionHandler;

import java.sql.*;

/**
 * DAO to inser an order
 */
public class InsertOrderDao {
    private String sqlOrder = "INSERT INTO orders " +
            "(order_customer_id, order_date, order_status) " +
            "VALUES (?, ?, ?)";
    String sqlOrderDetail =
            "INSERT INTO order_details "
                    + "(order_detail_order_id, order_detail_product_id, order_detail_quantity) "
                    + "VALUES (?, ?, ?)";

    /**
     * Inserts an order
     * @param orderDto Object with the information to insert
     * @return The ID of the order inserted
     */
    public int insertOrder(OrderDto orderDto) {
        int orderId = -1;

        try (Connection con = null;
             PreparedStatement ps = createOrderPreparedStatement(con, orderDto)
        ) {


            try (ResultSet result = null ) {
                if(!result.next()){

                } else {


                    for (OrderDetailDto orderDetailDTO : orderDto.getOrderDetail()) {


                        try (PreparedStatement detailsPS =
                                     createOrderDetailPreparedStatement(con, orderDetailDTO)) {

                        }
                    }

                }
            } catch(SQLException ex) {

                ExceptionHandler.handleException(ex);
            }
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex);
        }

        return orderId;
    }

    /**
     * Creates a PreparedStatement object to insert the order record
     * @param con Connnection object
     * @param orderDTO Object with the parameters to set on the PreparedStatement
     * @return A PreparedStatement object
     * @throws SQLException In case of an error
     */
    private PreparedStatement createOrderPreparedStatement(Connection con, OrderDto orderDTO) throws SQLException {

        return null;
    }

    /**
     * Creates a PreparedStatement object to insert the details of the order
     * @param con Connnection object
     * @param orderDetailDTO Object with the parameters to set on the PreparedStatement
     * @return A PreparedStatement object
     * @throws SQLException In case of an error
     */
    private PreparedStatement createOrderDetailPreparedStatement(Connection con, OrderDetailDto orderDetailDTO) throws SQLException {

        return null;
    }
}
