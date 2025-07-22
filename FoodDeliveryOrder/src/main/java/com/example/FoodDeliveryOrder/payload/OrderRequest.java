package com.example.FoodDeliveryOrder.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderRequest {
    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotNull(message = "Items list cannot be null")
    private List<String> items;

    @NotNull(message = "Total amount is required")
    @Min(value = 1, message = "Amount must be at least 1")
    private Double totalAmount;

    public OrderRequest(String customerName, List<String> items, Double totalAmount) {
        this.customerName = customerName;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
