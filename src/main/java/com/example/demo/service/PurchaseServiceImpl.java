// PurchaseServiceImpl.java
package com.example.demo.service;

import com.example.demo.models.Purchase;
import com.example.demo.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase savePurchase(Purchase purchase) {
        // Set createdAt and deliveryTime
        purchase.setCreatedAt(LocalDateTime.now());
        purchase.setDeliveryTime(LocalDateTime.now().plusHours(1));
        purchase.setOrderStatus("ожидается");
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }
}
