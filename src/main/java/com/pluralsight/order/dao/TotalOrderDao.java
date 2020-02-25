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

    /**
     * Gets the total of all paid orders of a customer
     * @param paramsDTO Object with the arguments of the operation
     * @return Total of all paid orders
     */
    public BigDecimal getTotalAllOrders(ParamsDto paramsDTO) {
        BigDecimal result = null;
      
        try (Connection con = Database.getInstance().getConnection();
             CallableStatement cs = createCallabeStatement(con, paramsDTO)
        ) {
            boolean success = cs.execute();

            if (success) {
                try(ResultSet resultSet = cs.getResultSet()){
                    if (resultSet.next()) {
                        result = resultSet.getBigDecimal(1);
                    }
                }
            }
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
        CallableStatement cs = con.prepareCall(query);
        cs.setLong(1, paramsDTO.getCustomerId());
        return cs;
    }
}
