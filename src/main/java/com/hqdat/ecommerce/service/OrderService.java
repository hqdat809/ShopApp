package com.hqdat.ecommerce.service;

import com.hqdat.ecommerce.dto.OrderDTO;
import com.hqdat.ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order createOrder(OrderDTO orderDTO);

    Order getOrderByID(Long orderID);

    Page<Order> getOrders(Pageable pageable);

    Page<Order> getOrdersByCategory(Pageable pageable, Long categoryID);

    Order updateOrder(Long orderID, OrderDTO orderDTO);

    void deleteOrder(Long orderID);
}
