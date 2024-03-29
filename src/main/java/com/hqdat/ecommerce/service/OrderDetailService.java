package com.hqdat.ecommerce.service;

import com.hqdat.ecommerce.dto.OrderDetailDTO;
import com.hqdat.ecommerce.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO);

    OrderDetail getOrderDetailByID(Long orderDetailID);

    List<OrderDetail> getOrderDetailsByOrder(Long orderID);

    OrderDetail updateOrderDetail(Long orderDetailID, OrderDetailDTO orderDetailDTO);

    void deleteOrderDetail(Long orderDetailID);
}
