package com.example.bikeservice.service;

import com.example.bikeservice.entity.Bike;
import com.example.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getBike(Long id) {
        return bikeRepository.findById(id).orElse(null);
    }
    public Bike createBike(Bike user) {
        return bikeRepository.save(user);
    }

    public List<Bike>getBikeByUserId(Long id){
        return bikeRepository.getBikesByUser_id(id);
    }
}
