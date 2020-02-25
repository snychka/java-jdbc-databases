package com.pluralsight.order.dao;

import com.pluralsight.order.dto.OrderDto;
import com.pluralsight.order.dto.OrderDetailDto;
import com.pluralsight.order.util.Database;
import com.pluralsight.order.util.ExceptionHandler;
import com.pluralsight.order.util.OrderStatus;

import java.sql.*;
import java.time.LocalDateTime;

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

        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement ps = createOrderPreparedStatement(con, orderDto)
        ) {
            con.setAutoCommit(false);
            ps.executeUpdate();

            try (ResultSet result = ps.getGeneratedKeys() ) {
                if(!result.next()){
                    con.rollback();
                } else {
                    orderId = result.getInt(1);

                    for (OrderDetailDto orderDetailDTO : orderDto.getOrderDetail()) {
                        orderDetailDTO.setOrderId(orderId);

                        try (PreparedStatement detailsPS =
                                     createOrderDetailPreparedStatement(con, orderDetailDTO)) {
                            int count = detailsPS.executeUpdate();

                            if (count != 1) {
                                con.rollback();
                                orderId = -1;
                            }
                        }
                    }
                    con.commit();
                }
            } catch(SQLException ex) {
                con.rollback();
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
        PreparedStatement ps = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
        ps.setLong(1, orderDTO.getCustomerId());
        ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(3, OrderStatus.CREATED.getStatus());

        return ps;
    }

    /**
     * Creates a PreparedStatement object to insert the details of the order
     * @param con Connnection object
     * @param orderDetailDTO Object with the parameters to set on the PreparedStatement
     * @return A PreparedStatement object
     * @throws SQLException In case of an error
     */
    private PreparedStatement createOrderDetailPreparedStatement(Connection con, OrderDetailDto orderDetailDTO) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sqlOrderDetail);
        ps.setLong(1, orderDetailDTO.getOrderId());
        ps.setLong(2, orderDetailDTO.getProductId());
        ps.setInt(3, orderDetailDTO.getQuantity());

        return ps;
    }
}
