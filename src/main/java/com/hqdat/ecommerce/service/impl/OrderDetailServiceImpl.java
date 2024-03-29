package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.dto.OrderDetailDTO;
import com.hqdat.ecommerce.exception.notfound.NotFoundException;
import com.hqdat.ecommerce.model.Order;
import com.hqdat.ecommerce.model.OrderDetail;
import com.hqdat.ecommerce.model.Product;
import com.hqdat.ecommerce.model.User;
import com.hqdat.ecommerce.repository.OrderDetailRepository;
import com.hqdat.ecommerce.repository.OrderRepository;
import com.hqdat.ecommerce.repository.ProductRepository;
import com.hqdat.ecommerce.repository.UserRepository;
import com.hqdat.ecommerce.service.OrderDetailService;
import com.hqdat.ecommerce.service.OrderService;
import com.hqdat.ecommerce.service.ProductService;
import com.hqdat.ecommerce.service.UserService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final OrderDetailRepository orderDetailRepository;


    public OrderDetailServiceImpl(ProductService productService, ProductRepository productRepository, UserRepository userRepository, UserService userService, OrderRepository orderRepository, OrderService orderService, OrderDetailRepository orderDetailRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail convertDTO(OrderDetailDTO orderDetailDTO) {
        Product existingProduct = productRepository.findById(orderDetailDTO.getProductID())
                .orElseThrow(() -> new NotFoundException("Product not found!!"));
        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderID())
                .orElseThrow(() -> new NotFoundException("Order not found!!"));

        return OrderDetail
                .builder()
                .product(existingProduct)
                .order(existingOrder)
                .price(existingProduct.getPrice())
                .quantity(orderDetailDTO.getQuantity())
                .color(orderDetailDTO.getColor())
                .build();
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = convertDTO(orderDetailDTO);
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailByID(Long orderDetailID) {
        return orderDetailRepository.findById(orderDetailID)
                .orElseThrow(() -> new NotFoundException("Order Details Not Found!!"));
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrder(Long orderID) {
        Order existingOrder = orderService.getOrderByID(orderID);

        return orderDetailRepository.findByOrder(existingOrder)
                .orElseThrow(() -> new NotFoundException("User Not Found!!"));
    }

    @Override
    public OrderDetail updateOrderDetail(Long orderDetailID, OrderDetailDTO orderDetailDTO) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findById(orderDetailID);

        if(existingOrderDetail.isPresent()) {
            OrderDetail orderDetail = existingOrderDetail.get();

            orderDetail.setColor(orderDetailDTO.getColor());
            orderDetail.setQuantity(orderDetailDTO.getQuantity());

            return orderDetailRepository.save(orderDetail);
        } else throw new NotFoundException("Order Details is not found!!");
    }

    @Override
    public void deleteOrderDetail(Long orderDetailID) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findById(orderDetailID);

        existingOrderDetail.ifPresent(orderDetailRepository::delete);
    }
}
