package com.example.carservice.service;

import com.example.carservice.entity.Car;
import com.example.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).orElse(null);
    }
    public Car createCar(Car user) {
        return carRepository.save(user);
    }

    public List<Car>getCarByUserId(Long id){
        return carRepository.getCarsByUser_id(id);
    }


}
