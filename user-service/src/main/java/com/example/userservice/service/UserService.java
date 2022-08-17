package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.feign_client.BikeFeignClient;
import com.example.userservice.feign_client.CarFeignClient;
import com.example.userservice.model.Bike;
import com.example.userservice.model.Car;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<Car> getCars(Long id){
        return restTemplate.getForObject("http://car-service/car/user/"+id, List.class);
    }
    public List<Bike> getBikes(Long id){
        return restTemplate.getForObject("http://bike-service/bike/user/"+id, List.class);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Car saveCar(Long id,Car car) {
        car.setUser_id(id);
        Car newCar=carFeignClient.save(car);
        return newCar;
    }

    public Bike saveBike(Long id,Bike bike) {
        bike.setUser_id(id);
        Bike newBike=bikeFeignClient.save(bike);
        return newBike;
    }
    public UserDto getUserDto(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        List<Car> cars = carFeignClient.getCars(id);
        List<Bike> bikes = bikeFeignClient.getBikes(id);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setCars(cars);
        userDto.setBikes(bikes);
        return userDto;

    }
}
