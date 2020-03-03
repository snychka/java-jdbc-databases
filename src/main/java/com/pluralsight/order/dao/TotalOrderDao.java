package com.pluralsight.order.dao;

import com.pluralsight.order.dto.ParamsDto;
import com.pluralsight.order.util.Database;
import com.pluralsight.order.util.ExceptionHandler;

import java.math.BigDecimal;
import java.sql.*;

/**
 * DAO to get the total of all the paid orders of a customer
 */
public class TotalOrderDao {
    private String query = "{call GET_PAID_ORDER_TOTAL_FROM_CUSTOMER(?)}";
    private Database database;

    /**
     * Constructor
     * @param database Database object
     */
    public TotalOrderDao(Database database) {
        this.database = database;
    }

    /**
     * Gets the total of all paid orders of a customer
     * @param paramsDTO Object with the arguments of the operation
     * @return Total of all paid orders
     */
    public BigDecimal getTotalAllOrders(ParamsDto paramsDTO) {
        BigDecimal result = null;

        try (Connection con = null;
             CallableStatement cs = createCallabeStatement(con, paramsDTO)
        ) {

        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex);
        }

        return result;
    }

    /**
     * Creates a PreparedStatement object to get the total of the orders
     * @param con Connnection object
     * @param paramsDTO Object with the parameters to set on the PreparedStatement
     * @return A PreparedStatement object
     * @throws SQLException In case of an error
     */
    private CallableStatement createCallabeStatement(Connection con, ParamsDto paramsDTO) throws SQLException {

        return null;
    }
}
