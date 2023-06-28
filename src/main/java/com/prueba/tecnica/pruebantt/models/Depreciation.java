package com.prueba.tecnica.pruebantt.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "depreciation")
public class Depreciation {

    public Depreciation() {
    }

    @Id
    @SequenceGenerator(name="seq_depreciation", sequenceName="seq_depreciation", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_depreciation")
    @Column(name = "id")
    private Integer id;
    @Column(name = "percentage", nullable = false)
    private BigDecimal percentage;
    @Column(name = "fiscal_year", nullable = false)
    private String fiscalYear;

    public Depreciation(int i, String number) {
    }

    public Depreciation(Integer o, String number) {
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
}
