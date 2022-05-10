package ro.sd.springdemo.service;

import ro.sd.springdemo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User saveUser(User user);

    User getUserByEmail(String email);
}
