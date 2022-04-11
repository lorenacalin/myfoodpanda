package ro.sd.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;
import ro.sd.springdemo.repository.FoodRepository;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getAllByRestaurantRestaurant_id(Integer id) {
        return foodRepository.getAllByRestaurantRestaurant_id(id);
    }

    @Override
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }
}
