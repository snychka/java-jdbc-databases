package com.pluralsight.order.dao;

import com.pluralsight.order.dto.OrderDto;
import com.pluralsight.order.dto.ParamsDto;
import com.pluralsight.order.util.ExceptionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO to get an order
 */
public class GetOrderDao {
    private String query = "SELECT * FROM orders o WHERE o.order_id = ?";

    /**
     * Gets an order by its ID
     * @param paramsDTO Object with the parameters for the operation
     * @return Object with the main information of an order
     */
    public OrderDto getOrderById(ParamsDto paramsDTO) {
        OrderDto orderDTO = null;

        try (Connection con = null;
             PreparedStatement ps = createPreparedStatement(con, paramsDTO.getOrderId());
             ResultSet rs = createResultSet(ps)
        ) {

        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex);
        }

        return orderDTO;
    }

    /**
     * Creates a PreparedStatement object to get an order
     * @param con Connnection object
     * @param orderId Order ID to set on the PreparedStatement
     * @return A PreparedStatement object
     * @throws SQLException In case of an error
     */
    private PreparedStatement createPreparedStatement(Connection con, long orderId) throws SQLException {

        return null;
    }

    /**
     * Creates a ResultSet object to get the results of the query
     * @param ps PreparedStatement object to create the query
     * @return A ResultSet object
     * @throws SQLException In case of an error
     */
    private ResultSet createResultSet(PreparedStatement ps) throws SQLException {
        return null;
    }
}
