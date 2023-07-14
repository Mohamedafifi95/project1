package com.skillstorm.warehouses.controllers;


import com.skillstorm.warehouses.messages.Message;
import com.skillstorm.warehouses.models.Electronic;
import com.skillstorm.warehouses.services.ElectronicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/electronics")
@CrossOrigin
public class ElectronicsController {
    


    @Autowired
    ElectronicsService electronicsService;



    @GetMapping
    public ResponseEntity<List<Electronic>> findAllElectronics() {
        List<Electronic> electronics = electronicsService.findAllElectronics();

        return new ResponseEntity<List<Electronic>>(electronics, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteByID(@PathVariable int id) {


        return electronicsService.deleteById(id);
    }
    @PostMapping("/add")
    public Message createElectronic(@Valid @RequestBody Electronic electronic) throws Exception {


//        Electronic newElectronic = electronicsService.saveElectronics(electronic);
        return electronicsService.saveElectronics(electronic);
    }




}
