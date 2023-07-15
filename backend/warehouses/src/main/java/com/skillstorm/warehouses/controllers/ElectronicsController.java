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

//@CrossOrigin annotaion for enable the access from another port and we should specify the company baseurl
@RestController
@RequestMapping("/electronics")
@CrossOrigin  //@CrossOrigin(origins = "http://localhost:5000")
public class ElectronicsController {
    


    @Autowired
    ElectronicsService electronicsService;

/*
* creating http requests
* getMapping: for creating get request to get data from database and will reformat to JSON payload
* postMapping: for creating post request to post JSON payload to and save it to the database
* PUTMapping: for updating the record by PUT request to post JSON payload to and save it to the database
* DELETEMapping:by passing record ID it will delete all record for this ID in the database
 * */

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



        return electronicsService.saveElectronics(electronic);
    }
    @PutMapping("/update")
    public Message updateElectronic(@Valid @RequestBody Electronic electronic) throws Exception {



        return electronicsService.saveElectronics(electronic);
    }




}
