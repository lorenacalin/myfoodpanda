package ro.sd.springdemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurant_id;

    @NotBlank(message = "Name cannot be blank, null or empty")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Location cannot be blank, null or empty")
    private String location;

    @Column(name = "delivery_zones")
    private String deliveryZones;

    @OneToOne(mappedBy = "restaurant")
    private User admin;

    @OneToMany(mappedBy = "restaurant", cascade=CascadeType.ALL)
    private Set<Food> foods;

    public Restaurant(){}

    public Restaurant(String name, String location, String deliveryZones, User admin) {
        this.name = name;
        this.location = location;
        this.deliveryZones = deliveryZones;
        this.admin = admin;
        this.foods = new HashSet<>();
    }
}
