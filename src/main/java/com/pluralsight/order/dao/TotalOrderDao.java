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
     * @param paramsDto Object with the arguments of the operation
     * @return Total of all paid orders
     */
    public BigDecimal getTotalAllPaidOrders(ParamsDto paramsDto) {
        BigDecimal result = null;

        try (Connection con = null;
             CallableStatement cs = createCallableStatement(con, paramsDto.getCustomerId())
        ) {

        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex);
        }

        return result;
    }

    /**
     * Creates a CallableStatement object to get the total of the orders
     * @param con Connnection object
     * @param customerId ID of the customer to set on the PreparedStatement
     * @return A PreparedStatement object
     * @throws SQLException In case of an error
     */
    private CallableStatement createCallableStatement(Connection con, long customerId) throws SQLException {

        return null;
    }
}
