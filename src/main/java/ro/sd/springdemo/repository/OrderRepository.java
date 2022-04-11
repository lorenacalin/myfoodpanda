package ro.sd.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.springdemo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
