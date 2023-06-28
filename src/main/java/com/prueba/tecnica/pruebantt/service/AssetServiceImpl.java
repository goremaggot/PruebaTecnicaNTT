package com.prueba.tecnica.pruebantt.service;

import com.prueba.tecnica.pruebantt.dto.AssetDTO;
import com.prueba.tecnica.pruebantt.exception.ResourceNotFoundException;
import com.prueba.tecnica.pruebantt.models.Asset;
import com.prueba.tecnica.pruebantt.models.Depreciation;
import com.prueba.tecnica.pruebantt.repository.AssetRepository;
import com.prueba.tecnica.pruebantt.repository.DepreciationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private DepreciationRepository depreciationRepository;

    @Override
    public AssetDTO consulta(Integer id) {
        AssetDTO assetDTO = AssetDTO.fromAsset(
                assetRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asset not found with id: " + id))
        );
        assetDTO.setValueWithDepreciation(calcularDepreciacion(assetDTO.toAsset()).intValue());
        return assetDTO;
    }

    @Override
    public AssetDTO crear(AssetDTO activo) {
        Asset savedAsset = assetRepository.save(activo.toAsset());
        return AssetDTO.fromAsset(savedAsset);
    }

    @Override
    public AssetDTO actualizar(Integer id, AssetDTO activo) {
        Asset activoActualizar = activo.toAsset();
        Optional<Asset> optionalActivo = assetRepository.findById(id);
        if (optionalActivo.isPresent()) {
            Asset activoExistente = optionalActivo.get();
            activoExistente.setSerialNumber(activoActualizar.getSerialNumber());
            activoExistente.setDescription(activoActualizar.getDescription());
            activoExistente.setName(activoActualizar.getName());
            activoExistente.setPurchase(activoActualizar.getPurchase());
            activoExistente = assetRepository.save(activoExistente);
            return AssetDTO.fromAsset(activoExistente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Asset not found with id: " + id);
        }
    }


    @Override
    public void eliminar(Integer id) {
        if (assetRepository.existsById(id)) {
            assetRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Asset not found with id: " + id);
        }
    }
    private BigDecimal calcularDepreciacion(Asset activo) {
        BigDecimal valorCompra = new BigDecimal(activo.getPurchase().getValuePurchase());
        int currentYear = Year.now().getValue();
        BigDecimal porcentajeDepreciacion = getPorcentajeDepreciacion(currentYear);
        int aniosPasados = calcularAniosPasados(activo.getPurchase().getDatePurchase());
        BigDecimal depreciacionAnual;
        BigDecimal depreciacionActualizada;
        if (aniosPasados == 0) {
            depreciacionActualizada = valorCompra;
        } else {
            depreciacionAnual = valorCompra.multiply(porcentajeDepreciacion).multiply(BigDecimal.valueOf(aniosPasados));
            depreciacionActualizada = valorCompra.subtract(depreciacionAnual);
        }
        return depreciacionActualizada;
    }

    private BigDecimal getPorcentajeDepreciacion(int currentYear) {
        Depreciation depreciation = depreciationRepository.findByFiscalYear(String.valueOf(currentYear)).orElseThrow(() -> new ResourceNotFoundException("Depreciation configuration not found for year: " + currentYear));
        return depreciation.getPercentage().divide(new BigDecimal(100));
    }

    private int calcularAniosPasados(LocalDate fechaCompra) {
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaCompra, fechaActual).getYears();
    }

}
