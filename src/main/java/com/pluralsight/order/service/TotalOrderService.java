package com.pluralsight.order.service;

import com.pluralsight.order.dao.TotalOrderDao;
import com.pluralsight.order.dto.ParamsDto;
import com.pluralsight.order.util.Database;

import java.math.BigDecimal;

/**
 * Service class to get the total of the orders of a customer
 */
public class TotalOrderService implements OrderService {
    private TotalOrderDao totalOrderDao = new TotalOrderDao(Database.getInstance());

    /**
     * Method to execute the service operation
     * @param  paramsDTO Object with the parameters to execute the service
     */
    @Override
    public String execute(ParamsDto paramsDTO) {
        String result;
        BigDecimal total = totalOrderDao.getTotalAllPaidOrders(paramsDTO);

        if(total != null) {
            result = "Total: " + total.toString();
        } else {
            result = "No paid orders for the customer with ID " + paramsDTO.getCustomerId() + " found";
        }
        return result;
    }
}
