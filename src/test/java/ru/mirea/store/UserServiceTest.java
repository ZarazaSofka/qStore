package ru.mirea.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.mirea.store.entity.Order;
import ru.mirea.store.entity.User;
import ru.mirea.store.repository.ClothRepository;
import ru.mirea.store.repository.OrderItemRepository;
import ru.mirea.store.repository.OrderRepository;
import ru.mirea.store.repository.UserRepository;
import ru.mirea.store.service.OrderService;
import ru.mirea.store.service.UserService;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private ClothRepository clothRepository;
    UserService userService;
    OrderService orderService;

    @Captor
    ArgumentCaptor<User> captor_user;


    @BeforeEach
    public void setUp() {
        orderService = new OrderService(userRepository, orderRepository, orderItemRepository, clothRepository);
        userService = new UserServiceTestImpl(userRepository, orderService, new BCryptPasswordEncoder());
    }
    @Test
    void loadUserByUsername() {
        Optional<User> user = Optional.of(new User());
        user.get().setUsername("user");
        Mockito.when(userRepository.findUserByUsername(user.get().getUsername())).thenReturn(user);

        Assertions.assertEquals(user.get().getUsername(), userService.loadUserByUsername(user.get().getUsername()).getUsername());
    }
    @Test
    void signUpUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Ron");
        user.setPassword("1111");
        user.setBasket_id(1L);
        userService.signUpUser(user);
        Mockito.verify(userRepository).save(captor_user.capture());
        User captured = captor_user.getValue();
        assertEquals("Response header [\" + username + \"]" ,"Ron", captured.getUsername());
        assertEquals("Response header [\" + basket_id + \"]" ,1L, captured.getBasket_id());
    }
}
