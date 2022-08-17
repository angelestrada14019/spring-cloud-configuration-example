package com.example.bikeservice.controller;

import com.example.bikeservice.entity.Bike;
import com.example.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> users = bikeService.getAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBike(@PathVariable Long id) {
        Bike user = bikeService.getBike(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Bike> createBike(@RequestBody Bike user) {
        Bike newUser = bikeService.createBike(user);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Bike>> getBikeByUserId(@PathVariable Long id){
        List<Bike> bikes = bikeService.getBikeByUserId(id);
        if (bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }
}
