package com.hqdat.ecommerce.controller;

import com.hqdat.ecommerce.dto.OrderDetailDTO;
import com.hqdat.ecommerce.model.OrderDetail;
import com.hqdat.ecommerce.service.OrderDetailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailByID(id));
    }

    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailsByOrder(orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(id, orderDetailDTO));
    }
}
