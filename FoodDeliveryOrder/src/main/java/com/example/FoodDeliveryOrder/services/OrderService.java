package com.example.FoodDeliveryOrder.services;

import com.example.FoodDeliveryOrder.entity.Order;
import com.example.FoodDeliveryOrder.payload.OrderRequest;
import com.example.FoodDeliveryOrder.payload.OrderResponse;
import com.example.FoodDeliveryOrder.payload.UpdateStatusRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);
    Page<OrderResponse> getAllOrders(Pageable pageable);
    OrderResponse getOrderStatus(Long id);
    OrderResponse updateOrderStatus(Long id, UpdateStatusRequest status);
    Order getOrderById(Long id);
}