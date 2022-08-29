package com.example.userservice.feign_client;

import com.example.userservice.model.Bike;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service")
@LoadBalancerClient(name = "bike-service",configuration = CustomLoadBalancerConfiguration.class)
public interface BikeFeignClient {

    @PostMapping("/bike")
    Bike save(@RequestBody Bike bike);

    @GetMapping("/bike/user/{id}")
    List<Bike> getBikes(@PathVariable Long id);
}
