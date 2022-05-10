package ro.sd.springdemo.service;

import ro.sd.springdemo.model.Food;

import java.util.List;

public interface FoodService {
    List<Food> getAllByRestaurantId(Integer id);

    Food saveFood(Food food);
}
