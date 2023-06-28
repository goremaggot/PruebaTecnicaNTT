package com.prueba.tecnica.pruebantt.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "assets")
public class Asset {
    public Asset() {
    }

    @Id
    @SequenceGenerator(name="seq_assets", sequenceName="seq_assets", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_assets")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_purchase", referencedColumnName = "id", nullable = false)
    @Cascade(CascadeType.ALL)
    private Purchase purchase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
