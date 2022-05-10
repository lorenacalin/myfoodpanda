package ro.sd.springdemo.service;

import ro.sd.springdemo.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    String getRestaurantNameWithId(Integer id);

    Restaurant getRestaurantByName(String name);

    Restaurant getById(Integer id);
}
