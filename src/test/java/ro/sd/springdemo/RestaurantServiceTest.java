package ro.sd.springdemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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
import ro.sd.springdemo.model.Restaurant;
import ro.sd.springdemo.model.User;
import ro.sd.springdemo.repository.RestaurantRepository;
import ro.sd.springdemo.service.RestaurantServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RestaurantServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant());
        given(restaurantRepository.findAll()).willReturn(restaurants);
        List<Restaurant> expectedRestaurantsList = restaurantService.getAllRestaurants();
        assertEquals(expectedRestaurantsList, restaurants);
        verify(restaurantRepository).findAll();
    }

    @Test
    public void saveRestaurantSuccessfully() {
        User admin = new User("passpass", "New Admin", "admin@yahoo.com", "admin");
        Restaurant restaurant = new Restaurant("New Restaurant", "Cluj-Napoca", "Cluj-Napoca", admin);
        admin.setRestaurant(restaurant);
        given(restaurantRepository.getRestaurantByName(restaurant.getName())).willReturn(Optional.empty());
        given(restaurantRepository.save(restaurant)).willAnswer(invocation -> invocation.getArgument(0));
        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);
        assertThat(savedRestaurant).isNotNull();
        verify(restaurantRepository).save(any(Restaurant.class));
    }

    @Test
    public void saveRestaurantWithExistingName() {
        User admin = new User("passpass", "New Admin", "admin@yahoo.com", "admin");
        Restaurant restaurant = new Restaurant("New Restaurant", "Cluj-Napoca", "Cluj-Napoca", admin);
        admin.setRestaurant(restaurant);
        given(restaurantRepository.getRestaurantByName(restaurant.getName())).willReturn(Optional.of(restaurant));
        assertThrows(RuntimeException.class, () -> {
            restaurantService.saveRestaurant(restaurant);
        });
        verify(restaurantRepository, never()).save(any(Restaurant.class));
    }

    @Test
    public void findRestaurantByName() {
        User admin = new User("passwordd", "Admin", "admin1@yahoo.com", "admin");
        Restaurant restaurant = new Restaurant("Mc", "Cluj-Napoca", "Cluj-Napoca", admin);
        admin.setRestaurant(restaurant);
        given(restaurantRepository.getRestaurantByName(restaurant.getName())).willReturn(Optional.of(restaurant));
        Restaurant expectedRestaurant = restaurantService.getRestaurantByName(restaurant.getName());
        assertEquals(restaurant, expectedRestaurant);
        verify(restaurantRepository).getRestaurantByName(restaurant.getName());
    }

    @Test
    public void findRestaurantWithNonExistingName() {
        User admin = new User("passwordd", "Admin", "admin1@yahoo.com", "admin");
        Restaurant restaurant = new Restaurant("Mc", "Cluj-Napoca", "Cluj-Napoca", admin);
        admin.setRestaurant(restaurant);
        given(restaurantRepository.getRestaurantByName(restaurant.getName())).willReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            restaurantService.getRestaurantByName(restaurant.getName());
        });
        verify(restaurantRepository).getRestaurantByName(restaurant.getName());
    }
}
