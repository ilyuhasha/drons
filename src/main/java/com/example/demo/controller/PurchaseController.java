package com.example.demo.controller;

import com.example.demo.models.Purchase;
import com.example.demo.service.PurchaseService;
import com.example.demo.service.UserService;
import com.example.demo.service.ProductService;
import com.example.demo.models.Users;
import com.example.demo.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        Purchase savedPurchase = purchaseService.savePurchase(purchase);
        return ResponseEntity.ok(savedPurchase);
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @GetMapping("/user-address/{userId}")
    public ResponseEntity<String> getAddressByUserId(@PathVariable Long userId) {
        Optional<Users> userOptional = userService.getUserById(userId);
        return userOptional.map(user -> ResponseEntity.ok(user.getAddress()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product-name/{productId}")
    public ResponseEntity<String> getProductNameByProductId(@PathVariable Long productId) {
        Optional<Product> productOptional = productService.getProductById(productId);
        return productOptional.map(product -> ResponseEntity.ok(product.getName()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user-name/{userId}")
    public ResponseEntity<String> getUserNameByUserId(@PathVariable Long userId) {
        Optional<Users> userOptional = userService.getUserById(userId);
        return userOptional.map(user -> ResponseEntity.ok(user.getUsername()))
                .orElse(ResponseEntity.notFound().build());
    }
}
