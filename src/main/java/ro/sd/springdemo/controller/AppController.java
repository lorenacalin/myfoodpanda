package ro.sd.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.sd.springdemo.model.Food;
import ro.sd.springdemo.model.Restaurant;
import ro.sd.springdemo.model.User;
import ro.sd.springdemo.model.enums.CategoryType;
import ro.sd.springdemo.repository.UserRepository;
import ro.sd.springdemo.service.UserService;

import javax.persistence.PostRemove;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserController userController;

    @Autowired
    private RestaurantController restaurantController;

    @Autowired
    private FoodController foodController;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("restaurant", new Restaurant());
        return "signup_form";
    }

    @GetMapping("/loginUser")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user, Restaurant restaurant) {
        String encodedPassword = "";
        for (int i = 0; i < user.getPassword().length(); i++) {
            int ascii = user.getPassword().charAt(i) + 1;
            encodedPassword += Character.valueOf((char) ascii);
        }
        user.setPassword(encodedPassword);
        if (user.getType().equals("admin")) {
            user.setRestaurant(restaurant);
            restaurantController.saveRestaurant(restaurant);
        }
        userController.saveUser(user);
        return "register_success";
    }

    @PostMapping("/process_login")
    public String processLogin(String email, String password, Model model, RedirectAttributes redirectAttributes) {
        User user = userController.findByEmail(email).getBody();
        if (user != null) {
            String encodedPassword = user.getPassword();
            String pass = "";
            for (int i = 0; i < encodedPassword.length(); i++) {
                int ascii = encodedPassword.charAt(i) - 1;
                pass += Character.valueOf((char) ascii);
            }
            if (password.equals(pass)) {
                model.addAttribute("user", user);
                model.addAttribute("restaurant", user.getRestaurant());
                redirectAttributes.addFlashAttribute("user1", user);
                if (user.getType().equals("client")) {
                    System.out.println(pass);
                    return "redirect:/restaurants";
                } else if (user.getType().equals("admin")) {
                    System.out.println(pass);
                    return "admin_main_page";
                }
            }
        }
        return "redirect:/loginUser";
    }

    @GetMapping("/restaurants")
    public String viewAllRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantController.getRestaurants().getBody();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    @GetMapping("/foods/restaurant/{id}")
    public String getFoodsByRestaurant(@PathVariable("id") Integer id, Model model) {
        List<Food> foods = foodController.getAllByRestaurantRestaurant_id(id).getBody();
        model.addAttribute("foods", foods);
        model.addAttribute("pageTitle", "Menu for " + restaurantController.getRestaurantNameWithId(id));
        return "foods_by_restaurant_id";
    }

    @GetMapping("/foodForm")
    public String showFoodForm(Model model) {
        model.addAttribute("food", new Food());
        model.addAttribute("restaurant", new Restaurant());
        return "food_form";
    }

    @PostMapping("/food/add")
    public String addFood(Food food, Restaurant restaurant) {
        food.setRestaurant(restaurant);
        foodController.saveFood(food);
        return "redirect:/loginUser";
    }
}
