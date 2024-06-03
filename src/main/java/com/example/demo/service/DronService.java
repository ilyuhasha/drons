package com.example.demo.service;

import com.example.demo.models.Dron;
import com.example.demo.models.Purchase;
import com.example.demo.repository.DronRepository;
import com.example.demo.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DronService {

    @Autowired
    private DronRepository dronRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;


    public Optional<String> getStatusById(Long id) {
        return dronRepository.findById(id)
                .map(dron -> dron.getStatus());
    }

    public Optional<String> getDeliveryTimeById(Long id) {
        return dronRepository.findById(id)
                .map(dron -> dron.getDeliveryTime().toString());
    }

    public Optional<Purchase> getPurchaseByDronId(Long id) {
        return dronRepository.findById(id)
                .flatMap(dron -> purchaseRepository.findById(dron.getPurchaseId()));
    }

    public void assignDroneToPurchase() {
        // Найти все покупки со статусом "ожидается"
        List<Purchase> pendingPurchases = purchaseRepository.findByOrderStatus("ожидается");

        // Найти свободные дроны
        List<Dron> freeDrones = dronRepository.findByStatus("свободен");

        for (Purchase purchase : pendingPurchases) {
            if (freeDrones.isEmpty()) {
                break; // Если свободных дронов нет, прекратить назначение
            }

            Dron dron = freeDrones.remove(0); // Взять первого свободного дрона
            dron.setPurchaseId(purchase.getId());
            dron.setDeliveryTime(LocalDateTime.now().plusMinutes(2));
            dron.setStatus("занят"); // Обновить статус дрона

            dronRepository.save(dron);

            // Обновить статус покупки на "доставляется"
            purchase.setOrderStatus("доставляется");
            purchaseRepository.save(purchase);
        }
    }

    @Scheduled(fixedRate = 60000) // Проверять каждую минуту
    public void updateDronStatus() {
        LocalDateTime now = LocalDateTime.now();
        List<Dron> drones = dronRepository.findAll();

        for (Dron dron : drones) {
            if (dron.getDeliveryTime() != null && dron.getDeliveryTime().isBefore(now) && "занят".equals(dron.getStatus())) {
                Optional<Purchase> purchaseOptional = purchaseRepository.findById(dron.getPurchaseId());
                if (purchaseOptional.isPresent()) {
                    Purchase purchase = purchaseOptional.get();
                    purchase.setOrderStatus("доставлен");
                    purchaseRepository.save(purchase);

                    // Освободить дрона
                    // dron.setPurchaseId(null);
                    // dron.setDeliveryTime(null);
                    dron.setStatus("свободен");
                    dronRepository.save(dron);
                }
            }
        }

        // Назначить дронов для новых покупок
        assignDroneToPurchase();
    }

    public List<Dron> getAllDrones() {
        return dronRepository.findAll();
    }

    public Dron saveDron(Dron dron) {
        return dronRepository.save(dron);
    }
}
