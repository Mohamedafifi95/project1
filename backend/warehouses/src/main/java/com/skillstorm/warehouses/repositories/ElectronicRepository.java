package com.skillstorm.warehouses.repositories;


import com.skillstorm.warehouses.models.Electronic;
import com.skillstorm.warehouses.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;



// JPARepository will give us build in methods as findAll and findById
@org.springframework.stereotype.Repository
public interface ElectronicRepository extends JpaRepository<Electronic, Integer> {



}
