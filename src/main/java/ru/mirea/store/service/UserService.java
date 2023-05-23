package ru.mirea.store.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.store.entity.User;
import ru.mirea.store.repository.UserRepository;

import java.text.MessageFormat;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with username {0} cannot be found.", username));
        }
    }

    public boolean signUpUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent())
            return false;
        log.info("Регистрация пользователя с ником {}", user.getUsername());
        final String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setEnabled(true);

        user = userRepository.save(user);

        user.setBasket_id(orderService.createBasket(user));
        userRepository.save(user);
        return true;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }
}
