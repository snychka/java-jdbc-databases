package com.pluralsight.order.service;

import com.pluralsight.order.dto.ParamsDto;

/**
 * Interface for service classes
 */
public interface OrderService {
    String execute(ParamsDto paramsDTO);
}
