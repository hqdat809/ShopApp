package com.hqdat.ecommerce.controller;

import com.hqdat.ecommerce.dto.OrderDetailDTO;
import com.hqdat.ecommerce.model.OrderDetail;
import com.hqdat.ecommerce.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping("")
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok(orderDetailService.createOrderDetail(orderDetailDTO));
    }
}
