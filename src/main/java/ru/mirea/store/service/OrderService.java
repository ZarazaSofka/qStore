package ru.mirea.store.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.store.entity.Order;
import ru.mirea.store.entity.OrderItem;
import ru.mirea.store.entity.User;
import ru.mirea.store.entity.cloth.Cloth;
import ru.mirea.store.repository.ClothRepository;
import ru.mirea.store.repository.OrderItemRepository;
import ru.mirea.store.repository.OrderRepository;
import ru.mirea.store.repository.UserRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Transactional
@Slf4j
@Service
@AllArgsConstructor
public class OrderService {
    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ClothRepository clothRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public Long createBasket(User user) {
        log.info("Инициализация корзины пользователя {}", user);
        Order order = new Order();
        order.setUser(user);
        return orderRepository.save(order).getId();
    }

    public void acceptOrder(Long user_id) {
        log.info("Подтверждение заказа пользователя с id {}", user_id);
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            Order order = orderRepository.findById(user.get().getBasket_id()).orElse(null);
            if (order != null) {
                order.setAccepted(true);
                order.setCreationDate(sdf.format(timestamp.getTime()));
                orderRepository.save(order);
                user.get().setBasket_id(createBasket(user.get()));
                userRepository.save(user.get());
            }
        }
    }

    public void addToBasket(Long user_id, Long cloth_id) {
        log.info("Добавление пользователю {} в корзину товара {}", user_id, cloth_id);
        Optional<User> user = userRepository.findById(user_id);
        Optional<Cloth> cloth = clothRepository.findById(cloth_id);
        if (user.isPresent() && cloth.isPresent()) {
            Order basket = orderRepository.findById(user.get().getBasket_id()).orElse(null);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(basket);
            orderItem.setCloth(cloth.get());
            orderItemRepository.save(orderItem);
            cloth.get().changeAmount(-1L);
            clothRepository.save(cloth.get());
        }
    }

    public void deleteFromBasket(Long user_id, Long order_item_id) {
        log.info("Удаление у пользователя {} элемента заказа {}", user_id, order_item_id);
        Optional<User> user = userRepository.findById(user_id);
        Optional<OrderItem> orderItem = orderItemRepository.findById(order_item_id);
        if (user.isPresent() && orderItem.isPresent()) {
            Order basket = orderRepository.findById(user.get().getBasket_id()).orElse(null);
            if (basket != null && basket.getOrderItems().contains(orderItem.get())) {
                orderItem.get().getCloth().changeAmount(1L);
                clothRepository.save(orderItem.get().getCloth());
                orderItemRepository.deleteById(order_item_id);
            }
        }
    }
}
