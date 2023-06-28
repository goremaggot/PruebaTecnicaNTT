package com.prueba.tecnica.pruebantt.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "purchases")
public class Purchase {

    public Purchase() {
    }

    @Id
    @SequenceGenerator(name="seq_purchases", sequenceName="seq_purchases", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_purchases")
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_purchase", nullable = false)
    private LocalDate datePurchase;
    @Column(name = "value_purchase", nullable = false)
    private Integer valuePurchase;

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
}
