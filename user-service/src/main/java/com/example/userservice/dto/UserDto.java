package com.example.userservice.dto;

import com.example.userservice.model.Bike;
import com.example.userservice.model.Car;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Car> cars;
    private List<Bike> bikes;
}
