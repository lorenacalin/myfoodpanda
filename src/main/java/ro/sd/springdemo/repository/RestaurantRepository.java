package ro.sd.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.sd.springdemo.model.Restaurant;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT r.name from Restaurant r where r.restaurant_id = ?1")
    Optional<String> getRestaurantNameWithId(Integer id);

    @Query("SELECT r from Restaurant r where r.name = ?1")
    Optional<Restaurant> getRestaurantByName(String name);

    @Query("SELECT r from Restaurant r where r.restaurant_id = ?1")
    Restaurant getById(Integer id);
}
