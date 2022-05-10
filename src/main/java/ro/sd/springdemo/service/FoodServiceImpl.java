package ro.sd.springdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;
import ro.sd.springdemo.repository.FoodRepository;
import ro.sd.springdemo.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

/**
 * Class containing all the methods you need for managing food
 */
@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * Gets the list of foods for the restaurant you give the id
     *
     * @param id - the id of the restaurant you want to see the foods of
     * @return the list containing all the food for the specific restaurant
     */
    @Override
    public List<Food> getAllByRestaurantId(Integer id) {
        log.info("FoodServiceImpl: Entering method getAllByRestaurantId");
        Optional<List<Food>> foods = foodRepository.getAllByRestaurantId(id);
        Restaurant restaurant = restaurantRepository.getById(id);
        if (restaurant != null) {
            log.info("FoodServiceImpl: Fetching list of foods for restaurant with id = {}", id);
            if (foods.isPresent()) {
                return foods.get();
            }
        }
        log.error("FoodServiceImpl: Restaurant with id = {} not found", id);
        throw new RuntimeException("Restaurant with id = " + id + " not found");
    }

    /**
     * Save food into the database
     *
     * @param food - food you want to save into the database
     * @return the food you added
     */
    @Override
    public Food saveFood(Food food) {
        log.info("FoodServiceImpl: Entering method saveFood");
        log.info("UserServiceImpl: Food with id = {} saved successfully", food.getFood_id());
        return foodRepository.save(food);
    }
}
