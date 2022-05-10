package ro.sd.springdemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;
import ro.sd.springdemo.model.User;
import ro.sd.springdemo.model.enums.CategoryType;
import ro.sd.springdemo.repository.FoodRepository;
import ro.sd.springdemo.repository.RestaurantRepository;
import ro.sd.springdemo.service.FoodServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FoodServiceTest {
    @Mock
    private FoodRepository foodRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private FoodServiceImpl foodService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveFoodSuccessfully() {
        User admin = new User("passpass", "New Admin", "admin@yahoo.com", "admin");
        Restaurant restaurant = new Restaurant("New Restaurant", "Cluj-Napoca", "Cluj-Napoca", admin);
        restaurant.setAdmin(admin);
        Food food = new Food("Italian pizza", "pizza with thin crust", 500.0, 28.5, CategoryType.PIZZA, restaurant);
        given(foodRepository.save(food)).willAnswer(invocation -> invocation.getArgument(0));
        Food savedFood = foodService.saveFood(food);
        assertThat(savedFood).isNotNull();
        verify(foodRepository).save(any(Food.class));
    }

    @Test
    public void getListOfFoodsByRestaurantId() {
        User admin = new User("passwordd", "Admin", "admin1@yahoo.com", "admin");
        Restaurant restaurant = new Restaurant("Mc", "Cluj-Napoca", "Cluj-Napoca", admin);
        admin.setRestaurant(restaurant);
        List<Food> foods = new ArrayList<>();
        foods.add(new Food());
        given(restaurantRepository.getById(restaurant.getRestaurant_id())).willReturn(restaurant);
        given(foodRepository.getAllByRestaurantId(restaurant.getRestaurant_id())).willReturn(Optional.of(foods));
        List<Food> expectedFoodList = foodService.getAllByRestaurantId(restaurant.getRestaurant_id());
        assertEquals(expectedFoodList, foods);
        verify(foodRepository).getAllByRestaurantId(restaurant.getRestaurant_id());
    }
}
