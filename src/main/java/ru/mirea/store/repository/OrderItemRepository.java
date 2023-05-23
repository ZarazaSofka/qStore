package ru.mirea.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.store.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
