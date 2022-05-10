package ro.sd.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.service.FoodService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/allfoods/restaurant/{id}")
    public ResponseEntity<List<Food>> getAllByRestaurantRestaurant_id(@PathVariable Integer id) {
        return new ResponseEntity<>(foodService.getAllByRestaurantId(id), HttpStatus.OK);
    }

    @PostMapping("/foods")
    public ResponseEntity<Food> saveFood(@Valid @RequestBody Food food) {
        return new ResponseEntity<>(foodService.saveFood(food), HttpStatus.CREATED);
    }
}
