package com.example.carservice.controller;

import com.example.carservice.entity.Car;
import com.example.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> users = carService.getAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        Car user = carService.getCar(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car user) {
        Car newUser = carService.createCar(user);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Car>> getCarByUserId(@PathVariable Long id){
        List<Car> cars = carService.getCarByUserId(id);
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
}
