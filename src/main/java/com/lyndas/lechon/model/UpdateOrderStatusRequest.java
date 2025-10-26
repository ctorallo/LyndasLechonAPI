package com.lyndas.lechon.model;

import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private OrderItem.Status status;
    private String orderId;
}
