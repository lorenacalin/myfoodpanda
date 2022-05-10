package ro.sd.springdemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ro.sd.springdemo.model.enums.CategoryType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer food_id;

    @NotBlank(message = "Name of the food cannot be blank, null or empty")
    private String name;

    private String description;

    @Min(0)
    private Double grams;

    @Min(0)
    private Double price;

    private CategoryType category;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "foods_orders", joinColumns = @JoinColumn(name = "food_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders;

    public Food() {
    }

    public Food(String name, String description, Double grams, Double price, CategoryType category, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.grams = grams;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
        this.orders = new HashSet<>();
    }
}
