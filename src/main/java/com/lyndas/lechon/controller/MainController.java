package com.lyndas.lechon.controller;

import com.lyndas.lechon.model.MenuItem;
import com.lyndas.lechon.model.OrderItem;
import com.lyndas.lechon.model.UpdateAvailabilityRequest;
import com.lyndas.lechon.model.UpdateOrderStatusRequest;
import com.lyndas.lechon.repository.MenuItemRepository;
import com.lyndas.lechon.repository.OrderItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;

    public MainController(MenuItemRepository menuItemRepository, OrderItemRepository orderItemRepository) {
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @RequestMapping("/")
    public String greet() {
        return "Welcome Milord!";
    }

    @RequestMapping("/about")
    @PreAuthorize("isAuthenticated()")
    public String about() {
        return "ABOUT METHOD";
    }

    @RequestMapping("/products")
    public List<MenuItem> listProducts() {
        return menuItemRepository.findAll();
    }

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('ADMIN')")
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @PatchMapping("/menu/update-availability")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuItem> updateAvailability(@RequestBody UpdateAvailabilityRequest request) {
        MenuItem existing = menuItemRepository.findByItemId(request.getItemId());

        if (existing != null) {
            existing.setAvailable(request.isAvailable());
            menuItemRepository.save(existing);
            return ResponseEntity.ok(existing);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/order")
    @PreAuthorize("hasRole('CUSTOMER')")
    public OrderItem placeOrder(@RequestBody OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @GetMapping("/list-orders")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderItem> getAllOrders() {
        return orderItemRepository.findAll();
    }

    @GetMapping("/orders/customer/{customerId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<OrderItem> getOrdersByCustomerId(@PathVariable String customerId) {
        return orderItemRepository.findByCustomerId(customerId);
    }

    @PatchMapping("/orders/status-update")
    @PreAuthorize("hasRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<OrderItem> updateOrderStatus(
            @RequestBody UpdateOrderStatusRequest statusRequest) {

        return orderItemRepository.findByOrderId(statusRequest.getOrderId()).map(order -> {
            orderItemRepository.save(order);
            return ResponseEntity.ok(order);
        }).orElse(ResponseEntity.notFound().build());
    }
}
