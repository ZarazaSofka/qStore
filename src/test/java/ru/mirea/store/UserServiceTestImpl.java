package ru.mirea.store;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mirea.store.entity.User;
import ru.mirea.store.repository.UserRepository;
import ru.mirea.store.service.OrderService;
import ru.mirea.store.service.UserService;

public class UserServiceTestImpl extends UserService {

    private final UserRepository userRepository;
    private final OrderService orderService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceTestImpl(UserRepository userRepository, OrderService orderService, PasswordEncoder passwordEncoder) {
        super(userRepository, orderService, passwordEncoder);
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean signUpUser(User user) {
        final String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }
}
