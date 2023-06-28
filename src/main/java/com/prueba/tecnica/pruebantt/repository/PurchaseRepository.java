package com.prueba.tecnica.pruebantt.repository;

import com.prueba.tecnica.pruebantt.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
}
