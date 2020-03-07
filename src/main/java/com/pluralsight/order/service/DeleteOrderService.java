package com.pluralsight.order.service;

import com.pluralsight.order.dao.DeleteOrderDao;
import com.pluralsight.order.dto.ParamsDto;
import com.pluralsight.order.util.Database;

/**
 * Service class to delete one or more orders
 */
public class DeleteOrderService implements OrderService {
    private DeleteOrderDao deleteOrderDao = new DeleteOrderDao(Database.getInstance());

    /**
     * Method to execute the service operation
     * @param  paramsDTO Object with the parameters to execute the service
     */
    @Override
    public String execute(ParamsDto paramsDTO) {
        String result;
        int rowsAffected = deleteOrderDao.deleteOrdersById(paramsDTO);

        if(rowsAffected <= 0) {
            result = "No rows affected";
        } else {
            result = "Rows affected: " + rowsAffected;
        }

        return result;
    }
}
