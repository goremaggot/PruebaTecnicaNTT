package com.prueba.tecnica.pruebantt.service;

import com.prueba.tecnica.pruebantt.dto.AssetDTO;
import com.prueba.tecnica.pruebantt.models.Asset;

import java.math.BigDecimal;
import java.util.List;

public interface AssetService {
    AssetDTO consulta(Integer id);
    AssetDTO crear(AssetDTO activo);
    AssetDTO actualizar(Integer id,AssetDTO activo);
    void eliminar(Integer id);
}
