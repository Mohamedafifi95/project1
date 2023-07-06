package com.skillstorm.warehouses.repositories;


import com.skillstorm.warehouses.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;



@org.springframework.stereotype.Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    


}
