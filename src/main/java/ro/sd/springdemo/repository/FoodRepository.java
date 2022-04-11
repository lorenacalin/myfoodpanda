package ro.sd.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Query("select f from Food f where f.restaurant.restaurant_id = ?1")
    List<Food> getAllByRestaurantRestaurant_id(Integer id);
}
