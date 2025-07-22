package com.example.FoodDeliveryOrder.repository;

import com.example.FoodDeliveryOrder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
