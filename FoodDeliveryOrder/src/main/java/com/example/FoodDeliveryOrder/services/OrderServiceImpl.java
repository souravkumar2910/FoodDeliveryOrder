package com.example.FoodDeliveryOrder.services;

import com.example.FoodDeliveryOrder.entity.Order;
import com.example.FoodDeliveryOrder.exception.OrderNotFoundException;
import com.example.FoodDeliveryOrder.payload.OrderRequest;
import com.example.FoodDeliveryOrder.payload.OrderResponse;
import com.example.FoodDeliveryOrder.payload.UpdateStatusRequest;
import com.example.FoodDeliveryOrder.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    @PostConstruct
    public void initConsumer() {
        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    Order order = orderQueue.take();
                    Thread.sleep(10000); // Simulate processing time
                    order.setStatus("processed");
                    orderRepository.save(order);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumerThread.setDaemon(true);
        consumerThread.start();
    }

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setItems(request.getItems());
        order.setTotalAmount(request.getTotalAmount());
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("pending");
        Order saved = orderRepository.save(order);
        orderQueue.offer(saved);

        return toResponse(order);
    }

    @Override
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(this::toResponse);
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id:"+id));
    }

    @Override
    public OrderResponse getOrderStatus(Long id) {
        Order order = getOrderById(id);
        return OrderResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .build();
    }

    @Override
    public OrderResponse updateOrderStatus(Long id, @RequestBody UpdateStatusRequest request) {

        Order order = getOrderById(id);

            order.setStatus(request.getStatus());
            orderRepository.save(order);
            return OrderResponse.builder()
                    .orderId(order.getId())
                    .status("Order has been "+request.getStatus())
                    .build();

    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getItems(),
                order.getTotalAmount(),
                order.getOrderTime(),
                order.getStatus()
        );
    }
}
