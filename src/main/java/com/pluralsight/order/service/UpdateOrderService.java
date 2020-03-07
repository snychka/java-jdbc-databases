package com.pluralsight.order.service;

import com.pluralsight.order.dao.UpdateOrderDao;
import com.pluralsight.order.dto.ParamsDto;
import com.pluralsight.order.util.Database;

/**
 * Service class to update an order
 */
public class UpdateOrderService implements OrderService {
    private UpdateOrderDao updateOrderDao = new UpdateOrderDao(Database.getInstance());

    /**
     * Method to execute the service operation
     * @param  paramsDTO Object with the parameters to execute the service
     */
    @Override
    public String execute(ParamsDto paramsDTO) {
        String result;
        int rowsAffected = updateOrderDao.updateOrderStatus(paramsDTO);

        if(rowsAffected <= 0) {
            result = "No rows affected";
        } else {
            result = "Rows affected: " + rowsAffected;
        }

        return result;
    }
}
