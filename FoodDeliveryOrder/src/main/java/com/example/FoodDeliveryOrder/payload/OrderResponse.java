package com.example.FoodDeliveryOrder.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private Long orderId;
    private String customerName;
    private List<String> items;
    private Double totalAmount;
    private LocalDateTime orderTime;
    private String status;


}
