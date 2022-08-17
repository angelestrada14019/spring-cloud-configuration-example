package com.example.userservice.feign_client;

import com.example.userservice.model.Car;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
@LoadBalancerClient(name = "car-service",configuration = CustomLoadBalancerConfiguration.class)
public interface CarFeignClient {

    @PostMapping("/car")
    Car save(@RequestBody Car car);

    @GetMapping("/car/user/{id}")
    List<Car> getCars(@PathVariable Long id);
}
