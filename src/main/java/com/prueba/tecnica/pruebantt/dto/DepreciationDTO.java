package com.prueba.tecnica.pruebantt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prueba.tecnica.pruebantt.models.Depreciation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO for Depreciation")
public class DepreciationDTO {
    public DepreciationDTO() {
    }

    @Schema(description = "Depreciation Id", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(description = "Depreciation percentage")
    private BigDecimal percentage;
    @Schema(description = "Depreciation Fiscal Year")
    private String fiscalYear;

    public DepreciationDTO(Object o, String number) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public static DepreciationDTO toDepreciationDTO(Depreciation depreciation) {
        DepreciationDTO depreciationDTO = new DepreciationDTO();
        depreciationDTO.setId(depreciation.getId());
        depreciationDTO.setPercentage(depreciation.getPercentage());
        depreciationDTO.setFiscalYear(depreciation.getFiscalYear());
        return depreciationDTO;
    }

    public static Depreciation toDepreciation(DepreciationDTO depreciationDTO) {
        Depreciation depreciation = new Depreciation();
        depreciation.setId(depreciationDTO.getId());
        depreciation.setPercentage(depreciationDTO.getPercentage());
        depreciation.setFiscalYear(depreciationDTO.getFiscalYear());
        return depreciation;
    }
}
