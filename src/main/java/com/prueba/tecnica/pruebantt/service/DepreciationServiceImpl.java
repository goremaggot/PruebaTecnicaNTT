package com.prueba.tecnica.pruebantt.service;

import com.prueba.tecnica.pruebantt.dto.DepreciationDTO;
import com.prueba.tecnica.pruebantt.exception.ResourceNotFoundException;
import com.prueba.tecnica.pruebantt.models.Depreciation;
import com.prueba.tecnica.pruebantt.repository.DepreciationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepreciationServiceImpl implements DepreciationService {

    @Autowired
    DepreciationRepository depreciationRepository;

    @Override
    public List<DepreciationDTO> listado() {
        List<Depreciation> depreciations = depreciationRepository.findAll();
        return depreciations.stream()
                .map(DepreciationDTO::toDepreciationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepreciationDTO consulta(Integer id) {
        Optional<Depreciation> optionalDepreciation = depreciationRepository.findById(id);
        if (optionalDepreciation.isPresent()) {
            return DepreciationDTO.toDepreciationDTO(optionalDepreciation.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Depreciation not found with id: " + id);
        }
    }

    @Override
    public DepreciationDTO crear(DepreciationDTO depreciation) {
        String fiscalYear = depreciation.getFiscalYear();
        Optional<Depreciation> existingDepreciation = depreciationRepository.findByFiscalYear(fiscalYear);
        if (existingDepreciation.isPresent()) {
            throw new IllegalArgumentException("Fiscal year already exists: " + fiscalYear);
        }
        Depreciation depreciation1 = depreciationRepository.save(DepreciationDTO.toDepreciation(depreciation));
        return DepreciationDTO.toDepreciationDTO(depreciation1);
    }

    @Override
    public DepreciationDTO actualizar(Integer id, DepreciationDTO depreciation) {
        Optional<Depreciation> optionalDepreciation = depreciationRepository.findById(id);
        if (optionalDepreciation.isPresent()) {
            depreciation.setId(id);
            Depreciation depreciation1 = depreciationRepository.save(DepreciationDTO.toDepreciation(depreciation));
            return  DepreciationDTO.toDepreciationDTO(depreciation1);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Depreciation not found with id: " + id);
        }
    }

    @Override
    public void eliminar(Integer id) {
        if (depreciationRepository.existsById(id)) {
            depreciationRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Depreciation not found with id: " + id);
        }
    }
}
