package ro.sd.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.sd.springdemo.model.Restaurant;
import ro.sd.springdemo.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/list_restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    public String getRestaurantNameWithId(Integer id) {
        return restaurantService.getRestaurantNameWithId(id);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> saveRestaurant(@Valid @RequestBody Restaurant restaurant) {
        return new ResponseEntity<Restaurant>(restaurantService.saveRestaurant(restaurant), HttpStatus.CREATED);
    }

    public Restaurant getById(Integer id) {
        return restaurantService.getById(id);
    }
}
