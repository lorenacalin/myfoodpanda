package ro.sd.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.sd.springdemo.model.Food;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Query("select f from Food f where f.restaurant.restaurant_id = ?1")
    Optional<List<Food>> getAllByRestaurantId(Integer id);
}
