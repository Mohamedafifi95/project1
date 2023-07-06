package com.skillstorm.warehouses.services;


import com.skillstorm.warehouses.messages.Message;
import com.skillstorm.warehouses.models.Electronic;
import com.skillstorm.warehouses.models.Warehouse;
import com.skillstorm.warehouses.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class WarehouseService {
    
    @Autowired
    WarehouseRepository warehouseRepository;

    Message m = new Message();

    public Warehouse newWarehouse(Warehouse warehouse) {

        return warehouseRepository.save(warehouse);
    }
    public List<Warehouse> findAllwarehoses() {
        return warehouseRepository.findAll();
    }

    public Message deleteById(int id) {
        try{

            m.setInfo(" warehouse: " + id +" has been deleted");
            warehouseRepository.deleteById(id);
            return m;
        }
        catch (Exception e) {
            m.setInfo("warehouse: " + id + " not found" );

        }

        return m;
    }


}
