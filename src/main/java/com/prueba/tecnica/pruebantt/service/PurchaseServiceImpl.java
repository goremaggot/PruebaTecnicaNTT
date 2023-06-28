package com.prueba.tecnica.pruebantt.service;

import com.prueba.tecnica.pruebantt.exception.ResourceNotFoundException;
import com.prueba.tecnica.pruebantt.models.Purchase;
import com.prueba.tecnica.pruebantt.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Override
    public List<Purchase> listado() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase consulta(Integer id) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        return optionalPurchase.orElseThrow(() -> new ResourceNotFoundException("Purchase not found with id: " + id));
    }

    @Override
    public Purchase crear(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase actualizar(Integer id, Purchase purchase) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        return optionalPurchase.map(existingPurchase -> {
            purchase.setId(id);
            return purchaseRepository.save(purchase);
        }).orElseThrow(() -> new ResourceNotFoundException("Purchase not found with id: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Purchase not found with id: " + id);
        }
    }
}
