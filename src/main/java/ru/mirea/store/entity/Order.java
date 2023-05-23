package ru.mirea.store.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", allocationSize = 1)
    @GeneratedValue(generator = "orders_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "accepted")
    private boolean accepted = false;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Override
    public String toString() {
        return "order(id = " + id + ", user id" + user.getId() + ", creation_date = " + creationDate +
                "clothes' ids: " + orderItems.stream().reduce("", (s, cloth)->s + ", " + cloth.getCloth().getId().toString(),
                (s1, s2)->s1 + ", " + s2) + ")";
    }
}
