package com.example.demo.controller;

import com.example.demo.models.Dron;
import com.example.demo.models.Product;
import com.example.demo.models.Purchase;
import com.example.demo.models.Users;
import com.example.demo.service.DronService;
import com.example.demo.service.ProductService;
import com.example.demo.service.PurchaseService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drons")
public class DronController {

    @Autowired
    private DronService dronService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Dron> getAllDrones() {
        return dronService.getAllDrones();
    }
    @GetMapping("/{id}/status")
    public ResponseEntity<String> getDronStatus(@PathVariable Long id) {
        Optional<String> status = dronService.getStatusById(id);
        return status.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/delivery-time")
    public ResponseEntity<String> getDronDeliveryTime(@PathVariable Long id) {
        Optional<String> deliveryTime = dronService.getDeliveryTimeById(id);
        return deliveryTime.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<String> getUserNameByDronId(@PathVariable Long id) {
        // Получаем покупку, связанную с дроном
        Optional<Purchase> purchaseOptional = dronService.getPurchaseByDronId(id);

        // Проверяем, существует ли покупка с данным дроном
        if (purchaseOptional.isPresent()) {
            Long userId = purchaseOptional.get().getUserId();

            // Получаем пользователя по его id
            Optional<Users> userOptional = userService.getUserById(userId);

            // Проверяем, существует ли пользователь с данным id
            if (userOptional.isPresent()) {
                // Возвращаем имя пользователя
                return ResponseEntity.ok(userOptional.get().getUsername());
            } else {
                // Если пользователь не найден, возвращаем сообщение об ошибке
                return ResponseEntity.notFound().build();
            }
        } else {
            // Если покупка не найдена, возвращаем сообщение об ошибке
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}/user-address")
    public ResponseEntity<String> getUserAddressByDronId(@PathVariable Long id) {
        // Получаем покупку, связанную с дроном
        Optional<Purchase> purchaseOptional = dronService.getPurchaseByDronId(id);

        // Проверяем, существует ли покупка с данным дроном
        if (purchaseOptional.isPresent()) {
            Long userId = purchaseOptional.get().getUserId();

            // Получаем пользователя по его id
            Optional<Users> userOptional = userService.getUserById(userId);

            // Проверяем, существует ли пользователь с данным id
            if (userOptional.isPresent()) {
                // Возвращаем адрес пользователя
                return ResponseEntity.ok(userOptional.get().getAddress());
            } else {
                // Если пользователь не найден, возвращаем сообщение об ошибке
                return ResponseEntity.notFound().build();
            }
        } else {
            // Если покупка не найдена, возвращаем сообщение об ошибке
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/product-name")
    public ResponseEntity<String> getProductNameByDronId(@PathVariable Long id) {
        Optional<Purchase> purchaseOptional = dronService.getPurchaseByDronId(id);

        if (purchaseOptional.isPresent()) {
            Long productId = purchaseOptional.get().getProductId();
            Optional<Product> productOptional = productService.getProductById(productId);

            if (productOptional.isPresent()) {
                return ResponseEntity.ok(productOptional.get().getName());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping
    public Dron createDron(@RequestBody Dron dron) {
        return dronService.saveDron(dron);
    }
}
