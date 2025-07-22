package com.example.FoodDeliveryOrder.controller;

import com.example.FoodDeliveryOrder.entity.Order;
import com.example.FoodDeliveryOrder.payload.OrderRequest;
import com.example.FoodDeliveryOrder.payload.OrderResponse;
import com.example.FoodDeliveryOrder.payload.UpdateStatusRequest;
import com.example.FoodDeliveryOrder.services.OrderService;
import com.example.FoodDeliveryOrder.services.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

//    @PostMapping
//    public OrderResponse placeOrder(@Valid @RequestBody OrderRequest request) {
//        return orderService.placeOrder(request);
//    }
//
//
//    @GetMapping
//    public Page<OrderResponse> getAllOrders(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return orderService.getAllOrders(PageRequest.of(page, size));
//    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest request) {
        OrderResponse orderPlaced = orderService.placeOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderPlaced);
    }

    @GetMapping
    public Page<OrderResponse> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return orderService.getAllOrders(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getAllOrders(@PathVariable Long id) {
        Order orderById = orderService.getOrderById(id);
        return ResponseEntity.ok(orderById);
    }



    @GetMapping("/{id}/status")
    public ResponseEntity<OrderResponse> getOrderStatus(@PathVariable Long id) {
        OrderResponse orderStatus = orderService.getOrderStatus(id);
        return ResponseEntity.ok(orderStatus);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusRequest request) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, request));
    }
}
