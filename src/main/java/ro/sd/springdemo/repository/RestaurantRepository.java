package ro.sd.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT r.foods from Restaurant r where r.restaurant_id = ?1")
    List<Food> getFoodsByRestaurant_id(Integer id);

    @Query("SELECT r.name from Restaurant r where r.restaurant_id = ?1")
    String getRestaurantNameWithId(Integer id);

    @Query("SELECT r from Restaurant r where r.restaurant_id = ?1")
    Restaurant findRestaurantByRestaurant_id(Integer id);

    Restaurant getRestaurantByName(String name);
}
