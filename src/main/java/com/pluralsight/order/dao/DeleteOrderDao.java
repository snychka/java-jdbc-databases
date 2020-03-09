package com.pluralsight.order.dao;

import com.pluralsight.order.dto.ParamsDto;
import com.pluralsight.order.util.Database;
import com.pluralsight.order.util.ExceptionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO to delete an order
 */
public class DeleteOrderDao {
    private Database database;

    /**
     * Constructor
     * @param database Database object
     */
    public DeleteOrderDao(Database database) {
        this.database = database;
    }

    /**
     * Deletes one or more orders using their IDs
     * @param paramsDto Object with the parameters for the operation
     * @return Number of orders deleted
     */
    public int deleteOrdersById(ParamsDto paramsDto) {
        int numberResults = 0;

        try (Connection con = null;
             PreparedStatement ps = createPreparedStatement(con, paramsDto.getOrderIds())
        ) {

        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex);
        }

        return numberResults;
    }

    /**
     * Method to build the delete SQL statement
     * @param orderIds IDs of the orders to delete
     * @return Delete SQL statement
     */
    private String buildDeleteSql(List<Long> orderIds) {
        String ids = null;

        return "DELETE FROM orders o WHERE o.order_id IN (" + ids + ")";
    }

    /**
     * Creates a PreparedStatement object to delete one or more orders
     * @param con Connnection object
     * @param orderIds Order IDs to set on the PreparedStatement
     * @return A PreparedStatement object
     * @throws SQLException In case of an error
     */
    private PreparedStatement createPreparedStatement(Connection con, List<Long> orderIds) throws SQLException {
        String sql = buildDeleteSql(orderIds);
        PreparedStatement ps = null;

        return ps;
    }
}
