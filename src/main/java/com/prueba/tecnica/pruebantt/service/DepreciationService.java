package com.prueba.tecnica.pruebantt.service;

import com.prueba.tecnica.pruebantt.dto.DepreciationDTO;

import java.util.List;

public interface DepreciationService {
    List<DepreciationDTO> listado();
    DepreciationDTO consulta(Integer id);
    DepreciationDTO crear(DepreciationDTO depreciation);
    DepreciationDTO actualizar(Integer id,DepreciationDTO depreciation);
    void eliminar(Integer id);
}
