package com.example.bikeservice.repository;

import com.example.bikeservice.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    @Query("select b from Bike b where b.user_id = ?1")
    public List<Bike> getBikesByUser_id(Long id);
}
