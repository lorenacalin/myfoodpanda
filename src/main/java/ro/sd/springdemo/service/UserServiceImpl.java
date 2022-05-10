package ro.sd.springdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.springdemo.model.User;
import ro.sd.springdemo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Class containing all the methods you need for managing users
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Gets a list containing all users
     *
     * @return the list of users
     */
    @Override
    public List<User> getAllUsers() {
        log.info("UserServiceImpl: Entering method getAllUsers");
        log.warn("List of users might be empty");
        return userRepository.findAll();
    }

    /**
     * Save user into the database
     *
     * @param user - user you want to save into the database
     * @return the user you added
     */
    @Override
    public User saveUser(User user) {
        log.info("UserServiceImpl: Entering method saveUser");
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            log.error("UserServiceImpl: User with email = {} already exists", user.getEmail());
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }
        log.info("UserServiceImpl: User with email = {} saved successfully!", user.getEmail());
        return userRepository.save(user);
    }

    /**
     * Get user which has the given email
     *
     * @param email - the email for the user you want to get
     * @return the user which has the specified email
     */
    @Override
    public User getUserByEmail(String email) {
        log.info("UserServiceImpl: Entering method getUserByEmail");
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.info("UserServiceImpl: Fetching user with email {}", email);
            return user.get();
        }
        log.error("UserServiceImpl: User with email = {} not found!", email);
        throw new RuntimeException("User with email = " + email + " not found!");
    }
}
