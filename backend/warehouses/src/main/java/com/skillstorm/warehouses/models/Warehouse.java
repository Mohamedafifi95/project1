package com.skillstorm.warehouses.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private int id;

    @Column(name = "location")
    private String location;
    @Column(name = "max_capacity")
    private int maxCapacity;

    @Column(name = "current_stock")
    private int currentStock;

    @OneToMany(targetEntity = Electronic.class, mappedBy = "warehouseID")
    private Set<Electronic> electronic;

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public Set<Electronic> getElectronic() {
        return electronic;
    }

    public void setElectronic(Set<Electronic> electronic) {
        this.electronic = electronic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", available=" + currentStock +
                ", electronic=" + electronic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;
        Warehouse warehouse = (Warehouse) o;
        return getId() == warehouse.getId() && getMaxCapacity() == warehouse.getMaxCapacity() && getCurrentStock() == warehouse.getCurrentStock() && getLocation().equals(warehouse.getLocation()) && getElectronic().equals(warehouse.getElectronic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLocation(), getMaxCapacity(), getCurrentStock(), getElectronic());
    }
}