package ro.sd.springdemo.service;

import ro.sd.springdemo.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order saveOrder(Order order);
    Order getOrderById(Integer id);
    void deleteOrder(Integer id);
    Order updateOrder(Order order);
}
