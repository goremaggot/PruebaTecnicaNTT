package com.prueba.tecnica.pruebantt.repository;

import com.prueba.tecnica.pruebantt.models.Depreciation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepreciationRepository extends JpaRepository<Depreciation,Integer> {
    Optional<Depreciation> findByFiscalYear(String fiscalYear);
}
