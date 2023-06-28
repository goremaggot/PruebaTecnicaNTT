package com.prueba.tecnica.pruebantt.controller;

import com.prueba.tecnica.pruebantt.dto.DepreciationDTO;
import com.prueba.tecnica.pruebantt.service.DepreciationService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depreciation")
public class DepreciationController {

    @Autowired
    private DepreciationService depreciationService;

    @Operation(summary = "Obtener todas las depreciaciones")
    @ApiResponse(responseCode = "200", description = "Lista de depreciaciones", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DepreciationDTO.class))
    })
    @GetMapping
    public ResponseEntity<List<DepreciationDTO>> getAllDepreciations() {
        List<DepreciationDTO> depreciations = depreciationService.listado();
        return new ResponseEntity<>(depreciations, HttpStatus.OK);
    }

    @Operation(summary = "Obtener una depreciación por ID")
    @ApiResponse(responseCode = "200", description = "Depreciación encontrada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DepreciationDTO.class))
    })
    @GetMapping("/{id}")
    public ResponseEntity<DepreciationDTO> getDepreciationById(@PathVariable Integer id) {
        DepreciationDTO depreciation = depreciationService.consulta(id);
        if (depreciation != null) {
            return new ResponseEntity<>(depreciation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Crear una nueva depreciación")
    @ApiResponse(responseCode = "201", description = "Depreciación creada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DepreciationDTO.class))
    })
    @PostMapping
    public ResponseEntity<DepreciationDTO> createDepreciation(@RequestBody DepreciationDTO depreciation) {
        DepreciationDTO createdDepreciation = depreciationService.crear(depreciation);
        return new ResponseEntity<>(createdDepreciation, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una depreciación por ID")
    @ApiResponse(responseCode = "200", description = "Depreciación actualizada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DepreciationDTO.class))
    })
    @PutMapping("/{id}")
    public ResponseEntity<DepreciationDTO> updateDepreciation(@PathVariable Integer id, @RequestBody DepreciationDTO depreciation) {
        DepreciationDTO updatedDepreciation = depreciationService.actualizar(id, depreciation);
        if (updatedDepreciation != null) {
            return new ResponseEntity<>(updatedDepreciation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar una depreciación por ID")
    @ApiResponse(responseCode = "204", description = "Depreciación eliminada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepreciation(@PathVariable Integer id) {
        depreciationService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
