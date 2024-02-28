package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.dto.OrderDTO;
import com.hqdat.ecommerce.exception.notfound.NotFoundException;
import com.hqdat.ecommerce.model.Order;
import com.hqdat.ecommerce.model.User;
import com.hqdat.ecommerce.repository.OrderRepository;
import com.hqdat.ecommerce.repository.UserRepository;
import com.hqdat.ecommerce.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order convertDTO(OrderDTO orderDTO) {

        User existingUser = userRepository.findById(orderDTO.getUserID())
                .orElseThrow(() -> new NotFoundException("User not found"));

        return Order
                .builder()
                .user(existingUser)
                .shippingDate(orderDTO.getShippingDate())
                .fullName(orderDTO.getFullName())
                .note(orderDTO.getNote())
                .paymentMethod(orderDTO.getPaymentMethod())
                .shippingDate(orderDTO.getShippingDate())
                .shippingMethod(orderDTO.getShippingMethod())
                .totalMoney(orderDTO.getTotalMoney())
                .active(orderDTO.isActive())
                .build();
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = convertDTO(orderDTO);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderByID(Long OrderID) {
        return orderRepository.findById(OrderID)
                .orElseThrow(() -> new NotFoundException("Order not found!!"));
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
