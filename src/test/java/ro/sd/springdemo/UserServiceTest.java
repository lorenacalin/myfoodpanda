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
import ro.sd.springdemo.model.User;
import ro.sd.springdemo.repository.UserRepository;
import ro.sd.springdemo.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        given(userRepository.findAll()).willReturn(users);
        List<User> expectedUsersList = userService.getAllUsers();
        assertEquals(expectedUsersList, users);
        verify(userRepository).findAll();
    }

    @Test
    public void saveUserSuccessfully() {
        User user = new User("lorena123", "Lorena Calin", "lorena@yahoo.com", "client");
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        given(userRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));
        User savedUser = userService.saveUser(user);
        assertThat(savedUser).isNotNull();
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void saveUserWithExistingEmail() {
        User user = new User("password", "Lorena", "lorena@yahoo.com", "client");
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
        assertThrows(RuntimeException.class, () -> {
            userService.saveUser(user);
        });
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void findUserByEmail() {
        User user = new User("pass123", "New User", "newuser@gmail.com", "client");
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));
        User expectedUser = userService.getUserByEmail(user.getEmail());
        assertEquals(user, expectedUser);
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    public void findUserWithNonExistingEmail() {
        User user = new User("pass123", "New User", "newuser@gmail.com", "client");
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            userService.getUserByEmail(user.getEmail());
        });
        verify(userRepository).findByEmail(user.getEmail());
    }
}
