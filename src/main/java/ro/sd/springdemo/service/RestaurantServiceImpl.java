package ro.sd.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;
import ro.sd.springdemo.model.User;
import ro.sd.springdemo.repository.RestaurantRepository;
import ro.sd.springdemo.repository.UserRepository;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant findRestaurantByRestaurant_id(Integer id) {
        return restaurantRepository.findRestaurantByRestaurant_id(id);
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Food> getFoodsByRestaurant_id(Integer id) {
        return restaurantRepository.getFoodsByRestaurant_id(id);
    }

    @Override
    public String getRestaurantNameWithId(Integer id) {
        return restaurantRepository.getRestaurantNameWithId(id);
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.getRestaurantByName(name);
    }
}
