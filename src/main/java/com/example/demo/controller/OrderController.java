//package com.example.demo.controller;
//
//import com.example.demo.models.Order;
//import com.example.demo.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestParam Long userId, @RequestParam Long productId) {
//        Order order = orderService.createOrder(userId, productId);
//        return ResponseEntity.ok(order);
//    }
//}
