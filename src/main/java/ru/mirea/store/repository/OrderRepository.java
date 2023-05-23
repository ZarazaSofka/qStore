package ru.mirea.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.store.entity.Order;
import ru.mirea.store.entity.User;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
