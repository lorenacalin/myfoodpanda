package ro.sd.springdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.springdemo.model.Restaurant;
import ro.sd.springdemo.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

/**
 * Class containing all the methods you need for managing restaurants
 */
@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * Save restaurant into the database
     *
     * @param restaurant - restaurant you want to save into the database
     * @return the restaurant you added
     */
    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        log.info("RestaurantService: Entering method saveRestaurant");
        Optional<Restaurant> restaurantOptional = restaurantRepository.getRestaurantByName(restaurant.getName());
        if (restaurantOptional.isPresent()) {
            log.error("RestaurantService: Restaurant with name = {} already exists", restaurant.getName());
            throw new RuntimeException("Restaurant with name " + restaurant.getName() + " already exists");
        }
        log.info("RestaurantService: Restaurant with name = {} saved successfully!", restaurant.getName());
        return restaurantRepository.save(restaurant);
    }

    /**
     * Gets a list containing all restaurants
     *
     * @return the list of all restaurants
     */
    @Override
    public List<Restaurant> getAllRestaurants() {
        log.info("RestaurantServiceImpl: Entering method getAllRestaurants");
        log.warn("List of restaurants might be empty");
        return restaurantRepository.findAll();
    }

    /**
     * Gets the restaurant's name for the specific id
     *
     * @param id - the id for the restaurant you want to get the name of
     * @return - the restaurant's name
     */
    @Override
    public String getRestaurantNameWithId(Integer id) {
        log.info("RestaurantServiceImpl: Entering method getRestaurantNameWithId");
        Optional<String> name = restaurantRepository.getRestaurantNameWithId(id);
        if (name.isPresent()) {
            log.info("RestaurantServiceImpl: Fetching name of restaurant with id = {}", id);
            return name.get();
        }
        log.error("RestaurantServiceImpl: Restaurant with id = {} not found!", id);
        throw new RuntimeException("Restaurant with id = " + id + " not found!");
    }

    /**
     * Gets the restaurant which has the name given as parameter
     *
     * @param name - the name you want to identify the restaurant by
     * @return the restaurant with the specific name
     */
    @Override
    public Restaurant getRestaurantByName(String name) {
        log.info("RestaurantServiceImpl: Entering method getRestaurantByName");
        Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByName(name);
        if (restaurant.isPresent()) {
            log.info("RestaurantServiceImpl: Fetching restaurant with name = {}", name);
            return restaurant.get();
        }
        log.error("RestaurantServiceImpl: Restaurant with name = {} not found!", name);
        throw new RuntimeException("Restaurant with name = " + name + " not found!");
    }

    /**
     * Gets the restaurant which has the id given as parameter
     *
     * @param id - id of the restaurant you want to get
     * @return the restaurant with the specific id
     */
    @Override
    public Restaurant getById(Integer id) {
        return restaurantRepository.getById(id);
    }
}
