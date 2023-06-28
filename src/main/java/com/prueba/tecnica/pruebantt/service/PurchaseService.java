package com.prueba.tecnica.pruebantt.service;

import com.prueba.tecnica.pruebantt.models.Asset;
import com.prueba.tecnica.pruebantt.models.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> listado();
    Purchase consulta(Integer id);
    Purchase crear(Purchase purchase);
    Purchase actualizar(Integer id,Purchase purchase);
    void eliminar(Integer id);
}
