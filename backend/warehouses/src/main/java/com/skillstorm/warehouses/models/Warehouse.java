package com.skillstorm.warehouses.models;

import javax.persistence.*;
import java.util.Objects;

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
        return "Movie{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", maxCapacity=" + maxCapacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return id == warehouse.id && maxCapacity == warehouse.maxCapacity && location.equals(warehouse.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, maxCapacity);
    }

}
