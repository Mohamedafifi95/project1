package com.skillstorm.warehouses.repositories;


import com.skillstorm.warehouses.models.Electronic;
import com.skillstorm.warehouses.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;




@org.springframework.stereotype.Repository
public interface ElectronicRepository extends JpaRepository<Electronic, Integer> {



}
