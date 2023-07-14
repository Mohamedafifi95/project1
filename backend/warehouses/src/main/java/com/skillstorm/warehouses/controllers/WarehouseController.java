package com.skillstorm.warehouses.controllers;


import com.skillstorm.warehouses.messages.Message;
import com.skillstorm.warehouses.models.Electronic;
import com.skillstorm.warehouses.models.Warehouse;
import com.skillstorm.warehouses.services.ElectronicsService;
import com.skillstorm.warehouses.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin

public class WarehouseController {


    @Autowired
    WarehouseService warehouseService;
    @Autowired
    ElectronicsService electronicsService;


    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllwarehoses() {
        List<Warehouse> warehouses = warehouseService.findAllwarehoses();

        return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
    }
    @PostMapping("/add")
    public Message addWarehous(@Valid @RequestBody Warehouse warehouse) {


        return warehouseService.newWarehouse(warehouse);
    }
    @PutMapping("/update")
    public Message updateWarehous(@Valid @RequestBody Warehouse warehouse) {


        return warehouseService.newWarehouse(warehouse);
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteByID(@PathVariable int id) {


        return warehouseService.deleteById(id);
    }



}
