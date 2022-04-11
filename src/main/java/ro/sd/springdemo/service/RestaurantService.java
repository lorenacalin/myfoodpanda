package ro.sd.springdemo.service;

import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant findRestaurantByRestaurant_id(Integer id);
    Restaurant saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
    List<Food> getFoodsByRestaurant_id(Integer id);
    String getRestaurantNameWithId(Integer id);
    Restaurant getRestaurantByName(String name);
}
