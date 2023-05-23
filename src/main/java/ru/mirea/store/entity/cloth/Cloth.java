package ru.mirea.store.entity.cloth;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clothes")
@Data
@NoArgsConstructor
public class Cloth {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "clothes_seq", sequenceName = "clothes_seq", allocationSize = 1)
    @GeneratedValue(generator = "clothes_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private ClothType type;

    @Column(name = "color")
    private ClothColor color;

    @Column(name = "size")
    private Integer size;

    @Column(name = "price")
    private Integer price;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "picture_url")
    private String picture_url;


    @Override
    public String toString() {
        return "order(id = " + id + ", name = " + name + ", type = " + type.getType() +
                ", color = " + color.getColor() + ", size = " + size + ", price = " +
                price + "руб, amount = " + amount + ", picture_url = " + picture_url + ")";
    }

    public void changeAmount(Long num) {
        amount = amount + num;
    }
}
