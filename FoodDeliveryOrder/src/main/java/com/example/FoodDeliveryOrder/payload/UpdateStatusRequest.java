package com.example.FoodDeliveryOrder.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    @NotNull
    private String status;
}
