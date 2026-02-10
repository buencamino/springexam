package com.upwork.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_stock")
public class ProductStock {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "vendor")
    private String vendor;

    protected ProductStock() {}

    public ProductStock(long id, String sku, String name, int quantity, String vendor) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.vendor = vendor;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
