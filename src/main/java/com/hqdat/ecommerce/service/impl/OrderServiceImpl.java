package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.dto.OrderDTO;
import com.hqdat.ecommerce.model.Order;
import com.hqdat.ecommerce.repository.OrderRepository;
import com.hqdat.ecommerce.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order convertDTO(OrderDTO orderDTO) {
        Order newOrder = Order
                .builder()
                .address(orderDTO.getAddress())
                .shippingDate(orderDTO.getShippingDate())
                .email(orderDTO.getEmail())
                .fullName(orderDTO.getFullName())
                .note(orderDTO.getNote())
                .paymentMethod(orderDTO.getPaymentMethod())
                .phoneNumber(orderDTO.getPhoneNumber())
                .shippingAddress(orderDTO.getShippingAddress())
                .shippingDate(orderDTO.getShippingDate())
                .shippingMethod(orderDTO.getShippingMethod())
                .totalMoney(orderDTO.getTotalMoney())
                .trackingNumber(orderDTO.getTrackingNumber())
                .active(orderDTO.isActive())
                .build();

        return newOrder;
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = convertDTO(orderDTO);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderByID(Long OrderID) {
        return orderRepository.findById(OrderID)
                .orElseThrow(() -> new RuntimeException("Order not found!!"));
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Order> getOrdersByCategory(Pageable pageable, Long categoryID) {
        return null;
    }

    @Override
    public Order updateOrder(Long OrderID, OrderDTO OrderDTO) {
        return null;
    }

    @Override
    public void deleteOrder(Long OrderID) {

    }
}
