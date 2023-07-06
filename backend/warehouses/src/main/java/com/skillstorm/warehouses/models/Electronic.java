package com.skillstorm.warehouses.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "electronics")
public class Electronic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "electronic_id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "price")
    private double price;

    @Column(name = "warehouse_id")
    private int warehouseID;

    @Column(name = "quantity")
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    @Override
    public String toString() {
        return "Electronic{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", warehouseID=" + warehouseID +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Electronic)) return false;
        Electronic that = (Electronic) o;
        return getId() == that.getId() && getYear() == that.getYear() && Double.compare(that.getPrice(), getPrice()) == 0 && getWarehouseID() == that.getWarehouseID() && getQuantity() == that.getQuantity() && getType().equals(that.getType()) && getModel().equals(that.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getModel(), getYear(), getPrice(), getWarehouseID(), getQuantity());
    }
}
