package com.lyndas.lechon.model;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemId implements Serializable {
    private String itemId;
    private MenuItem.Size size;
}
