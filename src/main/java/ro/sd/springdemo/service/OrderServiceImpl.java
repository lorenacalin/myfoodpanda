package ro.sd.springdemo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.springdemo.model.Order;
import ro.sd.springdemo.model.User;
import ro.sd.springdemo.model.enums.Status;
import ro.sd.springdemo.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //when user places an order
    @Override
    public Order saveOrder(Order order) {
        order.setStatus(Status.PENDING);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        throw new RuntimeException("Order with id = " + id + " not found.");
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }
}
