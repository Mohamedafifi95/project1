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
    public ResponseEntity<Electronic> createMovie(@Valid @RequestBody Electronic electronic) {


        Electronic newElectronic = electronicsService.saveElectronics(electronic);
        return new ResponseEntity<Electronic>(newElectronic, HttpStatus.CREATED);
    }




}
