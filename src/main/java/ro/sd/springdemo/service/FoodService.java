package ro.sd.springdemo.service;

import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;

import java.util.List;

public interface FoodService {
    List<Food> getAllByRestaurantRestaurant_id(Integer id);
    Food saveFood(Food food);
}
