package com.prueba.tecnica.pruebantt.dto;

import com.prueba.tecnica.pruebantt.models.Asset;
import com.prueba.tecnica.pruebantt.models.Purchase;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO for Asset")
public class AssetDTO {

    public AssetDTO() {
    }

    @Schema(description = "Asset Id", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(description = "Asset name")
    private String name;

    @Schema(description = "Serial number")
    private String serialNumber;

    @Schema(description = "Asset description")
    private String description;

    @Schema(description = "Date of purchase")
    private LocalDate datePurchase;

    @Schema(description = "Value of purchase")
    private Integer valuePurchase;

    @Schema(description = "Value with depreciation", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer valueWithDepreciation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDate datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Integer getValuePurchase() {
        return valuePurchase;
    }

    public void setValuePurchase(Integer valuePurchase) {
        this.valuePurchase = valuePurchase;
    }

    public Integer getValueWithDepreciation() {
        return valueWithDepreciation;
    }

    public void setValueWithDepreciation(Integer valueWithDepreciation) {
        this.valueWithDepreciation = valueWithDepreciation;
    }

    public static AssetDTO fromAsset(Asset asset) {
        AssetDTO assetDTO = new AssetDTO();
        assetDTO.setId(asset.getId());
        assetDTO.setName(asset.getName());
        assetDTO.setSerialNumber(asset.getSerialNumber());
        assetDTO.setDescription(asset.getDescription());
        assetDTO.setDatePurchase(asset.getPurchase().getDatePurchase());
        assetDTO.setValuePurchase(asset.getPurchase().getValuePurchase());
        return assetDTO;
    }

    public Asset toAsset() {
        Asset asset = new Asset();
        asset.setName(this.name);
        asset.setSerialNumber(this.serialNumber);
        asset.setDescription(this.description);

        Purchase purchase = new Purchase();
        purchase.setDatePurchase(this.datePurchase);
        purchase.setValuePurchase(this.valuePurchase);

        asset.setPurchase(purchase);

        return asset;
    }
}
