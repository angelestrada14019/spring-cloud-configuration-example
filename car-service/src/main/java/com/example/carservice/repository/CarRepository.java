package com.example.carservice.repository;

import com.example.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select c from Car c where c.user_id = ?1")
    List<Car> getCarsByUser_id(Long id);

}
