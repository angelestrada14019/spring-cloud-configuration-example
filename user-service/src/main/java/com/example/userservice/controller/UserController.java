package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.model.Bike;
import com.example.userservice.model.Car;
import com.example.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping("{id}/car")
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackGetCars")
    @Retry(name = "carsCB")
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
    private ResponseEntity<List<Car>> fallbackGetCars(@PathVariable Long id,RuntimeException e){
        return new ResponseEntity("El usuario " + id + ", no tiene los carros" +
                " disponibles en este momento",
                HttpStatus.OK);
    }
    @PostMapping("{id}/saveCar")
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackSaveCars")
    @Retry(name = "carsCB")
    public ResponseEntity<Car> saveCar(@PathVariable Long id, @RequestBody Car car){
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Car newCar = userService.saveCar(id, car);
        return ResponseEntity.ok(newCar);
    }
    private ResponseEntity<Car> fallbackSaveCars(@PathVariable Long id, @RequestBody Car car,RuntimeException e){
        return new ResponseEntity("El usuario " + id + ", no puede guardar" +
                " carros en este momento",
                HttpStatus.OK);
    }
    @GetMapping("{id}/bike")
    @Retry(name = "bikesCB")
    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackGetbikes")
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
    private ResponseEntity<List<Bike>> fallbackGetbikes(@PathVariable Long id,RuntimeException e){
        return new ResponseEntity("El usuario " + id + ", no tiene las motos" +
                " disponibles en este momento",
                HttpStatus.OK);
    }
    @PostMapping("{id}/saveBike")
    @Retry(name = "bikesCB")
    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackSaveBikes")
    public ResponseEntity<Bike> saveBike(@PathVariable Long id, @RequestBody Bike bike){
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Bike newBike = userService.saveBike(id, bike);
        return ResponseEntity.ok(newBike);
    }
    private ResponseEntity<Bike> fallbackSaveBikes(@PathVariable Long id, @RequestBody Bike bike,RuntimeException e){
        return new ResponseEntity("El usuario " + id + ", no puede guardar" +
                " motos en este momento",
                HttpStatus.OK);
    }
    @GetMapping("/userAll/{id}")
    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetAll")
    @Retry(name = "allCB")
    public ResponseEntity<UserDto> getUserDto(@PathVariable Long id) {
        UserDto userDto = userService.getUserDto(id);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }
    private ResponseEntity<UserDto> fallbackGetAll(@PathVariable Long id,RuntimeException e){
        return new ResponseEntity("El usuario " + id + ", no tiene los carros ni las motos" +
                " disponibles en este momento",
                HttpStatus.OK);
    }

}
