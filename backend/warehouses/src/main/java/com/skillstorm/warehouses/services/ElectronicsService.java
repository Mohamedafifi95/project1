package com.skillstorm.warehouses.services;


import com.skillstorm.warehouses.messages.Message;
import com.skillstorm.warehouses.models.Electronic;
import com.skillstorm.warehouses.models.Warehouse;
import com.skillstorm.warehouses.repositories.ElectronicRepository;
import com.skillstorm.warehouses.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ElectronicsService {
    
    @Autowired
    ElectronicRepository electronicRepository;

    Message m = new Message();


    public  List<Electronic> findAllElectronics() {
        return electronicRepository.findAll();
    }

    public  Electronic saveElectronics(Electronic electronic) {

        return electronicRepository.save(electronic);
    }


    public Message deleteById(int id) {
        try{

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
