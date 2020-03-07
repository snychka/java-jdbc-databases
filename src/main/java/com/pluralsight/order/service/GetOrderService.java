package com.pluralsight.order.service;

import com.pluralsight.order.dao.GetOrderDao;
import com.pluralsight.order.dto.OrderDto;
import com.pluralsight.order.dto.ParamsDto;
import com.pluralsight.order.util.Database;

import java.text.SimpleDateFormat;

/**
 * Service class to get the main information of an order
 */
public class GetOrderService implements OrderService {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private GetOrderDao getOrderDao = new GetOrderDao(Database.getInstance());

    /**
     * Method to execute the service operation
     * @param  paramsDTO Object with the parameters to execute the service
     */
    @Override
    public String execute(ParamsDto paramsDTO) {
        OrderDto orderDto = getOrderDao.getOrderById(paramsDTO);
        String result;

        if(orderDto != null) {
            result = String.format("Order ID: %d%n" +
                            "CustomerID: %d%n" +
                            "Status: %s%n" +
                            "Date: %s",
                    orderDto.getOrderId(),
                    orderDto.getCustomerId(),
                    orderDto.getStatus(),
                    sdf.format(orderDto.getDate())
            );
        } else {
            result = "Order with ID " + paramsDTO.getOrderId() + " was not found";
        }

        return result;
    }
}
