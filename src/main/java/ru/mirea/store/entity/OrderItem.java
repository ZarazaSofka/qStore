package ru.mirea.store.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.store.entity.cloth.Cloth;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "order_items_seq", sequenceName = "order_items_seq", allocationSize = 1)
    @GeneratedValue(generator = "order_items_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cloth_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cloth cloth;

    @Override
    public String toString() {
        return cloth.toString();
    }
}