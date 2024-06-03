// PurchaseService.java
package com.example.demo.service;

import com.example.demo.models.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase savePurchase(Purchase purchase);
    List<Purchase> getAllPurchases();

}
