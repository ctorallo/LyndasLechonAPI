package com.lyndas.lechon.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(MenuItemId.class)
public class MenuItem {

    @Id
    @Column(name = "item_id", nullable = false, length = 50)
    private String itemId;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Size size;

    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;

    public enum Size {
        Small, Medium, Large
    }
}
