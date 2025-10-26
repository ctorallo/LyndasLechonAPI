package com.lyndas.lechon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAvailabilityRequest {
    private String itemId;
    private boolean available;
}
