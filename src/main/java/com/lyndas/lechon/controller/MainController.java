package com.lyndas.lechon.controller;

import com.lyndas.lechon.model.MenuItem;
import com.lyndas.lechon.model.OrderItem;
import com.lyndas.lechon.repository.MenuItemRepository;
import com.lyndas.lechon.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
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
    public String greet(){
        return "Welcome Milord!";
    }

    @RequestMapping("/about")
    public String about(){
        return "ABOUT METHOD";
    }

    @RequestMapping("/products")
    public List<MenuItem> listProducts(){
        List<MenuItem> menuItems = Arrays.asList(
                MenuItem.builder()
                        .id(12345)
                        .name("Lechon Belly")
                        .description("Succulent roasted pork belly with crispy skin.")
                        .price(new BigDecimal("1200.00"))
                        .category("Main Dish")
                        .imageUrl("images/lechon_belly.jpg")
                        .availability(true)
                        .build(),

                MenuItem.builder()
                        .id(6789)
                        .name("Pancit Canton")
                        .description("Stir-fried egg noodles with vegetables, pork, and shrimp.")
                        .price(new BigDecimal("250.00"))
                        .category("Noodles")
                        .imageUrl("images/pancit_canton.jpg")
                        .availability(true)
                        .build(),

                MenuItem.builder()
                        .id(101112)
                        .name("Halo-Halo")
                        .description("Traditional Filipino dessert with shaved ice, milk, and mixed sweets.")
                        .price(new BigDecimal("120.00"))
                        .category("Dessert")
                        .imageUrl("images/halo_halo.jpg")
                        .availability(true)
                        .build(),

                MenuItem.builder()
                        .id(131415)
                        .name("Iced Calamansi Juice")
                        .description("Refreshing citrus drink made from calamansi.")
                        .price(new BigDecimal("60.00"))
                        .category("Drinks")
                        .imageUrl("images/calamansi_juice.jpg")
                        .availability(true)
                        .build()
        );

        return menuItems;

    }

    @PostMapping("/order")
    public OrderItem placeOrder(@RequestBody OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }


}
