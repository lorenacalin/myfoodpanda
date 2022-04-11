package ro.sd.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;
import ro.sd.springdemo.model.User;
import ro.sd.springdemo.repository.RestaurantRepository;
import ro.sd.springdemo.repository.UserRepository;
import ro.sd.springdemo.service.RestaurantService;
import ro.sd.springdemo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/list_restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants(){
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/food/restaurant/{id}")
    public ResponseEntity<List<Food>> getFoodsByRestaurant_id(@PathVariable Integer id){
        return new ResponseEntity<>(restaurantService.getFoodsByRestaurant_id(id), HttpStatus.OK);
    }

    public String getRestaurantNameWithId(Integer id){
        return restaurantService.getRestaurantNameWithId(id);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> saveRestaurant(@Valid @RequestBody Restaurant restaurant){
        return new ResponseEntity<Restaurant>(restaurantService.saveRestaurant(restaurant), HttpStatus.CREATED);
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantByRestaurant_id(@PathVariable Integer id){
        return new ResponseEntity<>(restaurantService.findRestaurantByRestaurant_id(id), HttpStatus.OK);
    }

    @GetMapping("/restaurantByName")
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String name){
        return new ResponseEntity<>(restaurantService.getRestaurantByName(name), HttpStatus.OK);
    }
}
