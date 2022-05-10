package ro.sd.springdemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ro.sd.springdemo.model.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private Integer order_id;

    @Column(name = "total_price")
    @Min(0)
    private Double totalPrice;

    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "orders")
    private Set<Food> foods;

    public Order() {
    }

    public Order(Double totalPrice, Status status, User user) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.user = user;
        this.foods = new HashSet<>();
    }
}
