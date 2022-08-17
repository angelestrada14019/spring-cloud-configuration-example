package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.model.Bike;
import com.example.userservice.model.Car;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("/userAll/{id}")
    public ResponseEntity<UserDto> getUserDto(@PathVariable Long id) {
        UserDto userDto = userService.getUserDto(id);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping("{id}/car")
    public ResponseEntity<List<Car>> getCars(@PathVariable Long id){
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(id);
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
    @GetMapping("{id}/bike")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable Long id){
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikes(id);
        if (bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("{id}/saveCar")
    public ResponseEntity<Car> saveCar(@PathVariable Long id, @RequestBody Car car){
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Car newCar = userService.saveCar(id, car);
        return ResponseEntity.ok(newCar);
    }
    @PostMapping("{id}/saveBike")
    public ResponseEntity<Bike> saveBike(@PathVariable Long id, @RequestBody Bike bike){
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Bike newBike = userService.saveBike(id, bike);
        return ResponseEntity.ok(newBike);
    }

}
