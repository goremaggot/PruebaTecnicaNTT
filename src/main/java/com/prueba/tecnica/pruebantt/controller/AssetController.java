package com.prueba.tecnica.pruebantt.controller;

import com.prueba.tecnica.pruebantt.dto.AssetDTO;
import com.prueba.tecnica.pruebantt.service.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Operation(summary = "Obtener un activo por ID")
    @ApiResponse(responseCode = "200", description = "Activo encontrado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AssetDTO.class))
    })
    @GetMapping("/{id}")
    public ResponseEntity<AssetDTO> getAssetById(@PathVariable Integer id) {
        AssetDTO asset = assetService.consulta(id);
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo activo")
    @ApiResponse(responseCode = "201", description = "Activo creado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AssetDTO.class))
    })
    @PostMapping
    public ResponseEntity<AssetDTO> createAsset(@RequestBody AssetDTO asset) {
        AssetDTO createdAsset = assetService.crear(asset);
        return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un activo por ID")
    @ApiResponse(responseCode = "200", description = "Activo actualizado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AssetDTO.class))
    })
    @PutMapping("/{id}")
    public ResponseEntity<AssetDTO> updateAsset(@PathVariable Integer id, @RequestBody AssetDTO asset) {
        AssetDTO updatedAsset = assetService.actualizar(id, asset);
        return new ResponseEntity<>(updatedAsset, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un activo por ID")
    @ApiResponse(responseCode = "204", description = "Activo eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Integer id) {
        assetService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

