package com.lyndas.lechon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // FK to users
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    private String branch;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "time_slot")
    private String timeSlot;

    @Column(name = "number_of_guests")
    private Integer numberOfGuests;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pending','confirmed','cancelled','completed') DEFAULT 'pending'")
    private Status status = Status.pending;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public enum Status {
        pending, confirmed, cancelled, completed
    }
}

