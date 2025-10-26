package com.lyndas.lechon.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @Column(name = "order_id", nullable = false, length = 50)
    private String orderId = UUID.randomUUID().toString();

    @Column(name = "employee_id", length = 50)
    private String employeeId;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "claim_date", nullable = false)
    private LocalDateTime claimDate;

    @Column(name = "item_id", nullable = false, length = 50)
    private String itemId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuItem.Size size;

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_type", nullable = false)
    private ClaimType claimType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @Column(name = "item_quantity", nullable = false)
    private int itemQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.pending;

    @Convert(converter = ExtrasConverter.class)
    private Map<String, Integer> extras;

    public enum ClaimType { delivery, pickup }
    public enum PaymentType { gcash, cash_on_delivery, bank_transfer }
    public enum Status { completed, cancelled, pending }
}
