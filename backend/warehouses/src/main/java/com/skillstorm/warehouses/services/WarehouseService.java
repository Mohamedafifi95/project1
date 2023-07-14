package com.skillstorm.warehouses.services;


import com.skillstorm.warehouses.messages.Message;
import com.skillstorm.warehouses.models.Electronic;
import com.skillstorm.warehouses.models.Warehouse;
import com.skillstorm.warehouses.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class WarehouseService {
    
    @Autowired
    WarehouseRepository warehouseRepository;

    Message m = new Message();

    public Message newWarehouse(Warehouse warehouse) {
            try{  warehouseRepository.save(warehouse);
                m.setInfo("saved");
        return m;
                }catch (Exception e ){
                m.setInfo("invalid input");

                return m;
            }
    }
    public List<Warehouse> findAllwarehoses() {
        return warehouseRepository.findAll();
    }

    public Message deleteById(int id) {
        try{
          Optional<Warehouse> w  = warehouseRepository.findById(id);
            System.out.println("wae" + w);
            if (w.get().getElectronic().size() != 0){
                m.setInfo("warehouse: " + id + " has items please move all item to another warehouse" );
                return m;
            }
            m.setInfo(" warehouse: " + id +" has been deleted");
            warehouseRepository.deleteById(id);
            return m;
        }
        catch (Exception e) {
            m.setInfo("warehouse: " + id + " doesn't exist" );

        }

        return m;
    }


}
