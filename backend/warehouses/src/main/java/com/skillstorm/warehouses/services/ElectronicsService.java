package com.skillstorm.warehouses.services;


import com.skillstorm.warehouses.messages.Message;
import com.skillstorm.warehouses.models.Electronic;
import com.skillstorm.warehouses.models.Warehouse;
import com.skillstorm.warehouses.repositories.ElectronicRepository;
import com.skillstorm.warehouses.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ElectronicsService {
    
    @Autowired
    ElectronicRepository electronicRepository;
    @Autowired
    WarehouseRepository warehouseRepository;


    Message m = new Message();


    public  List<Electronic> findAllElectronics() {
        return electronicRepository.findAll();
    }

    public  Message saveElectronics(Electronic electronic) throws Exception {

        try {
            int currentQuantity = electronic.getQuantity();

            if (electronic.getId() != 0) {
                Optional<Electronic> electronic2 = electronicRepository.findById(electronic.getId());

                int prevQuantity = electronic2.get().getQuantity();
                int newQuantity = electronic.getQuantity();

                currentQuantity = newQuantity - prevQuantity;
            }

            Optional<Warehouse> w = warehouseRepository.findById(electronic.getWarehouseID());

            int maxC = w.get().getMaxCapacity();
            int currentStock = w.get().getCurrentStock();
            int maxQuantaty = maxC - currentStock;

            if (currentQuantity + currentStock < maxC) {
                m.setInfo(" saved successfully");
                warehouseRepository.updateAvailable(currentQuantity, electronic.getWarehouseID());
                electronicRepository.save(electronic);
                return m;
            } else {
                m.setInfo("quantiy can't be more than " + maxQuantaty);

                return m;
            }
        }
        catch (NoSuchElementException ex){
            m.setInfo("warehouse ID : " + electronic.getWarehouseID() +" is wrong ");
            return  m;
        }
        catch (Exception e){
            m.setInfo("please review your input ");
           return  m;
        }

    }


    public Message deleteById(int id) {
        try{
            Optional<Electronic> electronic = electronicRepository.findById(id);

            electronic.get().getQuantity();

            Optional<Warehouse> w = warehouseRepository.findById(electronic.get().getWarehouseID());
            warehouseRepository.deleteAvailable(electronic.get().getQuantity(), electronic.get().getWarehouseID());

        m.setInfo(" itemID: " + id +" has been deleted");
        electronicRepository.deleteById(id);
        return m;
        }
        catch (Exception e) {
            m.setInfo("itemID: " + id + " not found" );

        }

        return m;
    }


}
