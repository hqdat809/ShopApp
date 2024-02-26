package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.dto.OrderDTO;
import com.hqdat.ecommerce.dto.OrderDetailDTO;
import com.hqdat.ecommerce.model.Category;
import com.hqdat.ecommerce.model.OrderDetail;
import com.hqdat.ecommerce.model.Product;
import com.hqdat.ecommerce.repository.OrderDetailRepository;
import com.hqdat.ecommerce.service.OrderDetailService;
import com.hqdat.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final ProductService productService;
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(ProductService productService, OrderDetailRepository orderDetailRepository) {
        this.productService = productService;
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail convertDTO(OrderDetailDTO orderDetailDTO) {
        Product existingProduct = productService.getProductByID(orderDetailDTO.getProductID());

        return OrderDetail
                .builder()
                .product(existingProduct)
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
                .orElseThrow(() -> new RuntimeException("Order Details Not Found!!"));
    }

    @Override
    public List<OrderDetail> getOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail updateOrderDetail(Long orderDetailID, OrderDetailDTO orderDetailDTO) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findById(orderDetailID);

        if(existingOrderDetail.isPresent()) {
            OrderDetail orderDetail = existingOrderDetail.get();

            orderDetail.setColor(orderDetailDTO.getColor());
            orderDetail.setPrice(orderDetailDTO.getPrice());
            orderDetail.setQuantity(orderDetailDTO.getQuantity());

        } else throw new RuntimeException("Order Details is not found!!");
        return null;
    }

    @Override
    public void deleteOrderDetail(Long orderDetailID) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findById(orderDetailID);

        existingOrderDetail.ifPresent(orderDetailRepository::delete);
    }
}
