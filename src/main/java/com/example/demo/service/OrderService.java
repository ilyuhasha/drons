//package com.example.demo.service;
//
//import com.example.demo.models.Order;
//import com.example.demo.models.Product;
//import com.example.demo.models.Users;
//import com.example.demo.repository.OrderRepository;
//import com.example.demo.repository.ProductRepository;
//import com.example.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//public class OrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    public Order createOrder(Long userId, Long productId) {
//        Optional<Users> user = userRepository.findById(userId);
//        Optional<Product> product = productRepository.findById(productId);
//
//        if (user.isPresent() && product.isPresent()) {
//            Order order = new Order();
//            order.setUser(user.get());
//            order.setProduct(product.get());
//            order.setOrderDate(LocalDateTime.now());
//            order.setDeliveryDate(LocalDateTime.now().plusHours(1));
//
//            return orderRepository.save(order);
//        } else {
//            throw new RuntimeException("User or Product not found");
//        }
//    }
//}
